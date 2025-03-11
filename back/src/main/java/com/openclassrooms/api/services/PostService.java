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

    // public PostDTO getPostById(Long postId) {
    //     // Récupérer le post avec ses informations de base
    //     Post post = postRepository.findById(postId)
    //                 .orElseThrow(() -> new RuntimeException("Post not found"));
        
    //     // Récupérer les IDs des commentaires liés au post
    //     Optional<Map<String, Object>> postWithCommentIdsOpt = postRepository.findOnePostByIdWithComments(postId);
        
    //     // Créer le DTO à partir du post
    //     PostDTO postDTO = new PostDTO();
    //     postDTO.setId(post.getId());
    //     postDTO.setTitle(post.getTitle());
    //     postDTO.setContent(post.getContent());
    //     postDTO.setCreatedAt(post.getCreatedAt());
        
    //     // Ajouter les informations de l'auteur
    //     if (post.getAuthor() != null) {
    //         postDTO.setAuthor(UserDTO.fromEntity(post.getAuthor()));
    //     }
        
    //     // Ajouter les informations du sujet
    //     if (post.getSubject() != null) {
    //         SubjectDTO subjectDTO = new SubjectDTO();
    //         subjectDTO.setId(post.getSubject().getId());
    //         subjectDTO.setName(post.getSubject().getName());
    //         subjectDTO.setDescription(post.getSubject().getDescription());
    //         postDTO.setSubject(subjectDTO);
    //     }
        
    //     // Ajouter les commentaires s'ils existent
    //     List<CommentDTO> commentDTOs = new ArrayList<>();
        
    //     if (postWithCommentIdsOpt.isPresent()) {
    //         Map<String, Object> postData = postWithCommentIdsOpt.get();
    //         String commentIdsStr = (String) postData.get("comment_ids");
            
    //         if (commentIdsStr != null && !commentIdsStr.isEmpty()) {
    //             String[] commentIds = commentIdsStr.split(",");
                
    //             for (String commentIdStr : commentIds) {
    //                 Long commentId = Long.parseLong(commentIdStr);
                    
    //                 // Récupérer chaque commentaire avec son auteur
    //                 Optional<Comment> commentOpt = commentRepository.findCommentByIdWithAuthor(commentId);
                    
    //                 if (commentOpt.isPresent()) {
    //                     Comment comment = commentOpt.get();
                        
    //                     // Créer le DTO du commentaire
    //                     CommentDTO commentDTO = new CommentDTO();
    //                     commentDTO.setId(comment.getId());
    //                     commentDTO.setContent(comment.getContent());
    //                     commentDTO.setCreatedAt(comment.getCreatedAt());
                        
    //                     // Ajouter uniquement le nom de l'auteur
    //                     if (comment.getAuthor() != null) {
    //                         commentDTO.setAuthorName(comment.getAuthor().getName());
    //                     }
                        
    //                     commentDTOs.add(commentDTO);
    //                 }
    //             }
    //         }
    //     }
        
    //     postDTO.setComments(commentDTOs);
    //     return postDTO;
    // }
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
