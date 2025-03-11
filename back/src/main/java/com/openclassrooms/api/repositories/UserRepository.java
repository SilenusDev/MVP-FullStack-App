package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.id, u.name, u.email, u.created_at, " +
           "(SELECT GROUP_CONCAT(s.id) " + 
           " FROM subjects s " +
           " JOIN subscriptions sub ON s.id = sub.subject_id " +
           " WHERE sub.user_id = u.id) AS subscribed_subject_ids " + 
           "FROM users u " +
           "WHERE u.id = :userId", nativeQuery = true)
    Optional<Map<String, Object>> findUserWithSubscribedSubjectIds(@Param("userId") Long userId);

    Optional<User> findById(Long id);
}
