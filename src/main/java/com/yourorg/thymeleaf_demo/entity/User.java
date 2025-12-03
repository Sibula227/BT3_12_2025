package com.yourorg.thymeleaf_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Admin")
    private boolean admin;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "Images")
    private String images;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;
}