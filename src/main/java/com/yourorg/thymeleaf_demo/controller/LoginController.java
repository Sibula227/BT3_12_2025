package com.yourorg.thymeleaf_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về file login.html
    }

    // --- THÊM ĐOẠN NÀY ---
    @GetMapping("/")
    public String home() {
        // Chuyển hướng về trang /categories
        // Spring Security sẽ tự chặn lại và bắt đăng nhập trước
        return "redirect:/categories"; 
    }
}