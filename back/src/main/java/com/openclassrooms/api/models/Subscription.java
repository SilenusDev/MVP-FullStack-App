package com.openclassrooms.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionId id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    public Subscription(SubscriptionId id, Subject subject) {
        this.id = id;
        this.subject = subject;
    }

    // Getters and Setters
    public SubscriptionId getId() {
        return id;
    }

    public void setId(SubscriptionId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

