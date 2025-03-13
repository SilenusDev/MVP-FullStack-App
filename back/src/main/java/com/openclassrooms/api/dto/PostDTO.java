package com.openclassrooms.api.dto;

import java.time.LocalDateTime;

public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UserDTO author;
    private SubjectDTO subject;
    private Long author_Id;
    private Long subject_Id;

    // Getters and Setters existants
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    // Nouveaux getters et setters pour les IDs
    public Long getAuthor_Id() {
        return author_Id;
    }

    public void setAuthor_Id(Long author_Id) {
        this.author_Id = author_Id;
    }

    public Long getSubject_Id() {
        return subject_Id;
    }

    public void setSubject_Id(Long subject_Id) {
        this.subject_Id = subject_Id;
    }
}

