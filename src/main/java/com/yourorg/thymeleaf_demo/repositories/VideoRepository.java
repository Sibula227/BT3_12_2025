package com.yourorg.thymeleaf_demo.repositories;

import com.yourorg.thymeleaf_demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoRepository extends JpaRepository<Video, String> {
    // Tìm kiếm video theo tiêu đề
    Page<Video> findByTitleContaining(String title, Pageable pageable);
}