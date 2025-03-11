package com.openclassrooms.api.repositories;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p, s, u " +
                "FROM Post p " +
                "JOIN p.subject s " +
                "JOIN s.subscriptions sub " +
                "JOIN p.author u " +
                "WHERE sub.user.id = :userId")
    List<Object[]> findPostsByUserSubscription(@Param("userId") Long userId);

    // @Query(value = "SELECT p.id, p.title, p.content, p.created_at, " +
    //                     "(SELECT GROUP_CONCAT(c.id) FROM comments c WHERE c.post_id = p.id) AS comment_ids " +
    //                     "FROM posts p " +
    //                     "WHERE p.id = :postId", nativeQuery = true)
    // Optional<Map<String, Object>> findOnePostByIdWithComments(@Param("postId") Long postId);
    
}
