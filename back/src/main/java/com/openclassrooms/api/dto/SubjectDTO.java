package com.openclassrooms.api.dto;

import java.time.LocalDateTime;
import com.openclassrooms.api.models.Subject;

public class SubjectDTO {

    private Long id; // Correction: passer à Long
    private String name;
    private String description;
    private LocalDateTime createdAt;

    // Constructeur avec un Subject
    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
        this.createdAt = subject.getCreatedAt();
    }

    // Constructeur par défaut (nécessaire pour Jackson)
    public SubjectDTO() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
