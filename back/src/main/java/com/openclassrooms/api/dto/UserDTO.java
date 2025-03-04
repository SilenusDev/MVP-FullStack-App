package com.openclassrooms.api.dto;

import java.time.LocalDateTime;

import com.openclassrooms.api.models.User;

public class UserDTO {
    private String name;
    private String email;
    private LocalDateTime createdAt;

    // Constructeur par défaut
    public UserDTO() {}

    // Constructeur privé pour créer une instance de UserDTO à partir d'un User
    private UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreated_at();
    }

    // Méthode statique pour créer un UserDTO à partir d'un User
    public static UserDTO fromEntity(User user) {
        return new UserDTO(user);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

