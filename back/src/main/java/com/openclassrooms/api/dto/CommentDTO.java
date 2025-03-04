package com.openclassrooms.api.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String content;
    private Long post_id;
    private Long author_id;
    private LocalDateTime createdAt;

    // Constructeurs
    public CommentDTO() {
    }

    public CommentDTO(Long id, String content, Long post_id, Long author_id, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.post_id = post_id;
        this.author_id = author_id;
        this.createdAt = createdAt;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
