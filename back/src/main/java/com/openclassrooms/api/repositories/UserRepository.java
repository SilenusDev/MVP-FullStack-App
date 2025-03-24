package com.openclassrooms.api.repositories;

import com.openclassrooms.api.models.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @EntityGraph(attributePaths = {"subscribedSubjects"})
    Optional<User> findWithSubscribedSubjectsById(Long userId);
}
