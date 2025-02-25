package com.openclassrooms.api.models;

import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Embeddable;


@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "subject_id")
    private Integer subjectId;

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
