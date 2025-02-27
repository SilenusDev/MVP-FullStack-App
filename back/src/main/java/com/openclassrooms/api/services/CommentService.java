package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.CommentDTO;
import com.openclassrooms.api.models.Comment;
import com.openclassrooms.api.models.Post;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.CommentRepository;
import com.openclassrooms.api.repositories.PostRepository;
import com.openclassrooms.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        return convertToDTO(savedComment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setAuthorId(comment.getAuthor().getId());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        // Récupérer le post et l'utilisateur
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User author = userRepository.findById(commentDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        comment.setPost(post);
        comment.setAuthor(author);

        return comment;
    }
}