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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO) {
        // Créer une nouvelle instance de Comment
        Comment comment = new Comment();
        
        // Définir le contenu
        comment.setContent(commentDTO.getContent());
        
        // Définir la date de création à maintenant
        comment.setCreatedAt(LocalDateTime.now());
        
        // Récupérer et définir l'auteur
        User author = userRepository.findById(commentDTO.getAuthor_id())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé avec l'ID: " + commentDTO.getAuthor_id()));
        comment.setAuthor(author);
        
        // Récupérer et définir le post
        Post post = postRepository.findById(commentDTO.getPost_id())
                .orElseThrow(() -> new RuntimeException("Post non trouvé avec l'ID: " + commentDTO.getPost_id()));
        comment.setPost(post);
        
        // Sauvegarder le commentaire
        comment = commentRepository.save(comment);
        
        // Convertir et retourner le DTO
        return convertToDTO(comment);
    }
    
    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        
        if (comment.getAuthor() != null) {
            dto.setAuthor_id(comment.getAuthor().getId());
        }
        
        if (comment.getPost() != null) {
            dto.setPost_id(comment.getPost().getId());
        }
        
        return dto;
    }
}