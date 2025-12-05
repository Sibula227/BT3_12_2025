package com.yourorg.thymeleaf_demo.controller;

import com.yourorg.thymeleaf_demo.entity.Category;
import com.yourorg.thymeleaf_demo.entity.Video;
import com.yourorg.thymeleaf_demo.service.CategoryService;
import com.yourorg.thymeleaf_demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class VideoRestController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

    // 1. Lấy danh sách Video (Có phân trang & Tìm kiếm)
    @GetMapping
    public ResponseEntity<Page<Video>> getAllVideos(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Video> videos = videoService.getAllVideos(keyword, page, size);
        return ResponseEntity.ok(videos);
    }

    // 2. Lấy 1 Video theo ID (Để hiển thị lên form sửa)
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable String id) {
        Optional<Video> video = videoService.findById(id);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Thêm mới hoặc Cập nhật Video
    @PostMapping
    public ResponseEntity<Video> saveVideo(@RequestBody Video video, @RequestParam("categoryId") Long categoryId) {
        // Gán Category cho Video (Vì JSON gửi lên chỉ có ID của Category)
        Optional<Category> category = categoryService.findById(categoryId);
        category.ifPresent(video::setCategory);
        
        videoService.save(video);
        return ResponseEntity.ok(video);
    }

    // 4. Xóa Video
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        videoService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    // 5. Lấy danh sách Category (Để đổ vào combobox)
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategoriesNoPage());
    }
}