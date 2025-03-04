package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Méthodes spécifiques si nécessaire
}