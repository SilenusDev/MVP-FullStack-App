package com.openclassrooms.api.repositories;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p, s, u " +
                "FROM Post p " +
                "JOIN p.subject s " +
                "JOIN s.subscriptions sub " +
                "JOIN p.author u " +
                "WHERE sub.user.id = :userId")
    List<Object[]> findPostsByUserSubscription(@Param("userId") Long userId);

    // @Query(value = "SELECT p.*, s.*, u.*" +
    //                "FROM posts p " +
    //                "JOIN subscriptions sub ON p.subject_id = sub.subject_id " +
    //                "JOIN subjects s ON p.subject_id = s.id " +
    //                "JOIN users u ON p.author_id = u.id " +
    //                "WHERE sub.user_id = :userId", nativeQuery = true)
    // List<Object[]>  findPostsByUserSubscription(@Param("userId") Long userId);
}
