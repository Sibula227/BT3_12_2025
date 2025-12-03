package com.yourorg.thymeleaf_demo.controller;

import com.yourorg.thymeleaf_demo.entity.User;
import com.yourorg.thymeleaf_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Hiển thị danh sách + Phân trang + Tìm kiếm
    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        Page<User> userPage = userService.getAllUsers(keyword, page, 5); // 5 user mỗi trang
        model.addAttribute("userPage", userPage);
        model.addAttribute("keyword", keyword);
        return "user/list"; // Trả về file list.html trong folder templates/user
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add-edit"; // Dùng chung form cho add và edit
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable("username") String username, Model model) {
        Optional<User> user = userService.findById(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/add-edit";
        }
        return "redirect:/users";
    }

    // Xử lý lưu (Thêm mới hoặc Cập nhật)
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // Xóa user
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect:/users";
    }
}