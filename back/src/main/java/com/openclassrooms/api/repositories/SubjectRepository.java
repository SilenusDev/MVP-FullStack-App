package com.openclassrooms.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.api.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findById(Long id);
    // Ajoutez cette méthode pour rechercher un sujet par son nom
    Optional<Subject> findByName(String name);
}

