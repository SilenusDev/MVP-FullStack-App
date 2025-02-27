package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.models.Post;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.PostRepository;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    // public List<PostDTO> getSubscribedPosts(Long userId) {
    //     List<Long> subscribedSubjectIds = getSubscribedSubjectIds(userId);
    //     List<Post> posts = postRepository.findBySubjectIdIn(subscribedSubjectIds);
    //     return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    // }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDTO(post);
    }

        public PostDTO createPost(PostDTO postDTO) {
        // Validation des données
        validatePostDTO(postDTO);

        // Conversion du DTO en entité
        Post post = convertToEntity(postDTO);

        // Sauvegarde de l'entité
        Post savedPost = postRepository.save(post);

        // Conversion de l'entité en DTO
        return convertToDTO(savedPost);
    }

    private void validatePostDTO(PostDTO postDTO) {
        if (postDTO.getAuthorId() == null || postDTO.getSubjectId() == null || postDTO.getTitle() == null || postDTO.getContent() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authorId, subjectId, title, and content are required");
        }
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedAt(LocalDateTime.now());

        // Récupérer l'auteur et le sujet
        User author = userRepository.findById(postDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        Subject subject = subjectRepository.findById(postDTO.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));

        post.setAuthor(author);
        post.setSubject(subject);

        return post;
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setAuthorId(post.getAuthor().getId());
        postDTO.setSubjectId(post.getSubject().getId());
        return postDTO;
    }

    // private List<Long> getSubscribedSubjectIds(Long userId) {
    //     // Implémenter la logique pour récupérer les IDs des sujets abonnés
    //     return userRepository.findSubscribedSubjectIdsByUserId(userId);
    // }
}