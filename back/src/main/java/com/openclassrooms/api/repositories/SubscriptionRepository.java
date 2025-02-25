package com.openclassrooms.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s.subject FROM Subscription s WHERE s.user.id = :userId")
    List<Subject> findSubjectsByUserId(@Param("userId") Long userId);
}
