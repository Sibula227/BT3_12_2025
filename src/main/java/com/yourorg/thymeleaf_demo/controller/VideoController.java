package com.yourorg.thymeleaf_demo.controller;

import com.yourorg.thymeleaf_demo.entity.Category;
import com.yourorg.thymeleaf_demo.entity.Video;
import com.yourorg.thymeleaf_demo.service.CategoryService; 
import com.yourorg.thymeleaf_demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService; // Cần cái này để đổ vào combobox

    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        Page<Video> videoPage = videoService.getAllVideos(keyword, page, 5);
        model.addAttribute("videoPage", videoPage);
        model.addAttribute("keyword", keyword);
        return "video/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("video", new Video());
        // Lấy danh sách Categories để hiển thị trong thẻ <select>
        // Lưu ý: Bạn cần viết hàm findAll() trong CategoryService trả về List<Category> (không phân trang)
        // Nếu chưa có, hãy dùng categoryRepository.findAll() tạm thời hoặc viết thêm vào Service
        model.addAttribute("categories", categoryService.getAllCategoriesNoPage()); 
        return "video/add-edit";
    }

    @GetMapping("/edit/{videoId}")
    public String showEditForm(@PathVariable("videoId") String videoId, Model model) {
        Optional<Video> video = videoService.findById(videoId);
        if (video.isPresent()) {
            model.addAttribute("video", video.get());
            model.addAttribute("categories", categoryService.getAllCategoriesNoPage());
            return "video/add-edit";
        }
        return "redirect:/videos";
    }

    @PostMapping("/save")
    public String saveVideo(@ModelAttribute("video") Video video) {
        videoService.save(video);
        return "redirect:/videos";
    }

    @GetMapping("/delete/{videoId}")
    public String deleteVideo(@PathVariable("videoId") String videoId) {
        videoService.delete(videoId);
        return "redirect:/videos";
    }
 
    @GetMapping("/ajax")
    public String ajaxPage() {
        return "video/ajax_video";
    }
}