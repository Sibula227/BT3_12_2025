package com.yourorg.thymeleaf_demo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Long categoryId;

    @Column(name = "Categoryname")
    private String categoryName;

    @Column(name = "Categorycode")
    private String categoryCode;

    @Column(name = "Images")
    private String images;

    @Column(name = "Status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User user; // User tạo category

    @OneToMany(mappedBy = "category")
    private List<Video> videos;
}