package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.models.Post;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.PostRepository;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

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
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("enter in getPostsByUserSubscription");
        List<Object[]> results = postRepository.findPostsByUserSubscription(userId);
        //affichher le userId
        System.out.println("userId: " + userId);
        // afficher les résultats
        results.forEach(result -> {
            for (Object o : result) {
                System.out.println("post: " + o);
            }
        });
        System.out.println("------------------------------------------------------------------------------------------------------");
        return results.stream().map(this::mapToPostDTO).collect(Collectors.toList());
    }

    public PostDTO createPost(PostDTO postDTO) {
        // Création d'un nouveau post
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedAt(LocalDateTime.now()); // Définir createdAt à maintenant

        // Récupérer et assigner le sujet
        if (postDTO.getSubject() != null && postDTO.getSubject().getName() != null) {
            Subject subject = subjectRepository.findByName(postDTO.getSubject().getName())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            post.setSubject(subject);
        }

        // Récupérer et assigner l'auteur
        if (postDTO.getAuthor() != null && postDTO.getAuthor().getEmail() != null) {
            User author = userRepository.findByEmail(postDTO.getAuthor().getEmail())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
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
