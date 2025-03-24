package com.openclassrooms.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.openclassrooms.api.models.User;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private List<SubjectDTO> subscribedSubjects;

    public UserDTO() {}

    // Ajoutez cette méthode statique pour transformer un User en UserDTO
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreated_at());
        
        // Récupérer les sujets abonnés
        if (user.getSubscriptions() != null) {
            dto.setSubscribedSubjects(
                user.getSubscriptions().stream()
                    .map(subscription -> SubjectDTO.fromEntity(subscription.getSubject()))
                    .collect(Collectors.toList())
            );
        }       
        return dto;
    }

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

    public List<SubjectDTO> getSubscribedSubjects() {
        return subscribedSubjects;
    }

    public void setSubscribedSubjects(List<SubjectDTO> subscribedSubjects) {
        this.subscribedSubjects = subscribedSubjects;
    }
}

