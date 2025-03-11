package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.author WHERE c.post.id = :postId")
    List<Comment> findCommentsByPostID(Long postId);
}