package com.yourorg.thymeleaf_demo.repositories;

import com.yourorg.thymeleaf_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, String> {
    // Tìm kiếm theo Username hoặc Fullname
    Page<User> findByUsernameContainingOrFullnameContaining(String username, String fullname, Pageable pageable);
}