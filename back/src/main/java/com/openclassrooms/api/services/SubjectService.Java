package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.repositories.SubjectRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Récupère la liste des sujets disponibles sous forme de DTO.
     */
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectDTO::fromEntity) // Utilisation de la méthode fromEntity
                .collect(Collectors.toList());
    }
}
