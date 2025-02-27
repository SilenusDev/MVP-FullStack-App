package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // Ajouter des méthodes de requête personnalisées si nécessaire
}
