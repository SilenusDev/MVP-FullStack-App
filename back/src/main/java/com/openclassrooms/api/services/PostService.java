package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.CommentDTO;
import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.models.Comment;
import com.openclassrooms.api.models.Post;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.CommentRepository;
import com.openclassrooms.api.repositories.PostRepository;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDTO(post);
    }

    public List<PostDTO> getPostsByUserSubscription(Long userId) {

        List<Object[]> results = postRepository.findPostsByUserSubscription(userId);

        // afficher les résultats
        results.forEach(result -> {
            for (Object o : result) {

            }
        });

        return results.stream().map(this::mapToPostDTO).collect(Collectors.toList());
    }

    public PostDTO createPost(PostDTO postDTO) {
        // Création d'un nouveau post
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedAt(LocalDateTime.now());
    
        // Récupérer et assigner le sujet à partir de subject_Id
        Long subjectId = postDTO.getSubject_Id();
        if (subjectId != null) {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
            post.setSubject(subject);
        }
    
        // Récupérer et assigner l'auteur à partir de author_Id
        Long authorId = postDTO.getAuthor_Id();
        if (authorId != null) {
            User author = userRepository.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found with ID: " + authorId));
            post.setAuthor(author);
        }
    
        // Sauvegarder le post
        Post savedPost = postRepository.save(post);
    
        // Convertir le post sauvegardé en DTO pour le retour
        return convertToDTO(savedPost);
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());

        if (post.getSubject() != null) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setName(post.getSubject().getName());
            subjectDTO.setDescription(post.getSubject().getDescription());
            postDTO.setSubject(subjectDTO);
        }

        if (post.getAuthor() != null) {
            UserDTO authorDTO = new UserDTO();
            authorDTO.setName(post.getAuthor().getName());
            authorDTO.setEmail(post.getAuthor().getEmail());
            authorDTO.setCreatedAt(post.getAuthor().getCreated_at());
            postDTO.setAuthor(authorDTO);
        }

        return postDTO;
    }

    private PostDTO mapToPostDTO(Object[] result) {
        // Extraire les objets de la requête
        Post post = (Post) result[0];
        Subject subject = (Subject) result[1];
        User author = (User) result[2];
    
        // Créer le PostDTO
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
    
        // Créer le SubjectDTO
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setName(subject.getName());
        subjectDTO.setDescription(subject.getDescription());
        postDTO.setSubject(subjectDTO);
    
        // Créer le UserDTO
        UserDTO authorDTO = new UserDTO();
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setCreatedAt(author.getCreated_at()); // Assurez-vous que ce champ existe dans User
        postDTO.setAuthor(authorDTO);
    
        return postDTO;
    }
}
