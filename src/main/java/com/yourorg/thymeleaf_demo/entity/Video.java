package com.yourorg.thymeleaf_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Videos")
public class Video {
    @Id
    @Column(name = "VideoId")
    private String videoId;

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
    @ToString.Exclude 
    private Category category;
}