package com.openclassrooms.api.dto;

import com.openclassrooms.api.models.Subject;

public class SubjectDTO {
    private String name;
    private String description;

    // Constructeur par défaut
    public SubjectDTO() {}

    // Constructeur qui accepte un objet Subject
    public SubjectDTO(Subject subject) {
        this.name = subject.getName();
        this.description = subject.getDescription();
    }

    // Getters and Setters
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

