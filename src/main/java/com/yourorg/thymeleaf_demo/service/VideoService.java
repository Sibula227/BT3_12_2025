package com.yourorg.thymeleaf_demo.service;

import com.yourorg.thymeleaf_demo.entity.Video;
import com.yourorg.thymeleaf_demo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public Page<Video> getAllVideos(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return videoRepository.findByTitleContaining(keyword, pageable);
        }
        return videoRepository.findAll(pageable);
    }

    public Optional<Video> findById(String videoId) {
        return videoRepository.findById(videoId);
    }

    public void save(Video video) {
        videoRepository.save(video);
    }

    public void delete(String videoId) {
        videoRepository.deleteById(videoId);
    }
}