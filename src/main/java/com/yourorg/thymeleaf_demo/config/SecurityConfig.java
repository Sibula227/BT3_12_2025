package com.yourorg.thymeleaf_demo.config;

import com.yourorg.thymeleaf_demo.service.MyUserServicesDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public MyUserServicesDetail userDetailsService() {
        return new MyUserServicesDetail();
    }

    // Mã hóa mật khẩu (BCrypt là chuẩn hiện nay)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Cho phép truy cập không cần login vào các file tĩnh (css, js, ảnh) và trang login
                .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/register").permitAll()
                // Tất cả các request khác đều CẦN PHẢI LOGIN
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login") // Đường dẫn tới trang login tự tạo
                .defaultSuccessUrl("/categories", true) // <--- QUAN TRỌNG: Login xong chuyển về categories
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Logout xong quay về login
                .permitAll()
            );

        return http.build();
    }
}