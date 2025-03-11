package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Subscription;
import com.openclassrooms.api.models.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    List<Subscription> findAllByUserId(Long userId);
    List<Subscription> findByUserId(Long userId);
    void deleteByUserIdAndSubjectId(Long userId, Long subjectId);
    boolean existsByUserIdAndSubjectId(Long userId, Long subjectId);

    // Trouve une souscription par utilisateur et sujet
    Optional<Subscription> findByUserIdAndSubjectId(Long userId, Long subjectId);
}
