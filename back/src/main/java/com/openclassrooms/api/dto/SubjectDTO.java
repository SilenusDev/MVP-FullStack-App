package com.openclassrooms.api.dto;

import com.openclassrooms.api.models.Subject;

public class SubjectDTO {
    private Long id;
    private String name;
    private String description;

    public SubjectDTO() {}

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
    }

    // Getters et Setters
    // Ajoutez cette méthode pour convertir un Subject en SubjectDTO
    public static SubjectDTO fromEntity(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setDescription(subject.getDescription());
        return dto;
    }


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
}
