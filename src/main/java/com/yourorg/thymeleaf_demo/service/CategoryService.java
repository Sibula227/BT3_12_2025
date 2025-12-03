package com.yourorg.thymeleaf_demo.service;

import com.yourorg.thymeleaf_demo.entity.Category;
import com.yourorg.thymeleaf_demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Lấy danh sách có phân trang và tìm kiếm (Dùng cho trang List Category)
    public Page<Category> getAllCategories(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return categoryRepository.findByCategoryNameContaining(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    // 2. Lấy TOÀN BỘ danh sách không phân trang (Dùng cho Combobox bên Video)
    public List<Category> getAllCategoriesNoPage() {
        return categoryRepository.findAll();
    }

    // 3. Tìm theo ID (Dùng cho chức năng Edit)
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // 4. Lưu hoặc Cập nhật
    public void save(Category category) {
        categoryRepository.save(category);
    }

    // 5. Xóa
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}