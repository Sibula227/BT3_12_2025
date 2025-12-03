package com.yourorg.thymeleaf_demo.service;

import com.yourorg.thymeleaf_demo.entity.User;
import com.yourorg.thymeleaf_demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return userRepository.findByUsernameContainingOrFullnameContaining(keyword, keyword, pageable);
        }
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(String username) {
        return userRepository.findById(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }
}