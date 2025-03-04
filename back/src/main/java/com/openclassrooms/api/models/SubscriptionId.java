package com.openclassrooms.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subject_id")
    private Long subjectId;

    // Constructeur par défaut (nécessaire pour JPA)
    public SubscriptionId() {}

    // Nouveau constructeur
    public SubscriptionId(Long userId, Long subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
