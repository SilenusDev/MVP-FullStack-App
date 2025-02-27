package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.Subscription;
import com.openclassrooms.api.models.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    List<Subscription> findByUserId(Integer userId);

    void deleteByUserIdAndSubjectId(Integer userId, Integer subjectId);

    boolean existsByUserIdAndSubjectId(Integer userId, Integer subjectId);
}
