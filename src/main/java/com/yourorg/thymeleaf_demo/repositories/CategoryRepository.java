package com.yourorg.thymeleaf_demo.repositories;

import com.yourorg.thymeleaf_demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Lưu ý: Category dùng ID là Long (tự tăng)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Tìm kiếm theo tên Category
    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}