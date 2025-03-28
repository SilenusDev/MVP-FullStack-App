package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findBySubject_Subscriptions_UserId(Long userId);
   
}
