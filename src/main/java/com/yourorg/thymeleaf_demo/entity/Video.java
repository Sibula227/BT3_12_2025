package com.yourorg.thymeleaf_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Videos")
public class Video {
    @Id // Đây là khóa chính duy nhất
    @Column(name = "VideoId")
    private String videoId; // Kiểu String (Ví dụ: V001, V002...)

    @Column(name = "Title")
    private String title;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views")
    private int views;

    @Column(name = "Description")
    private String description;

    @Column(name = "Active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
    
    // ĐÃ XÓA đoạn private Long id; gây lỗi
}