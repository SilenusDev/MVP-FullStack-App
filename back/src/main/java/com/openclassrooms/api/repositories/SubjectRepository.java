package com.openclassrooms.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.api.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}

