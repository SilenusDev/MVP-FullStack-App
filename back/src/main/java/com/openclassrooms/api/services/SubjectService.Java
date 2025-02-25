package com.openclassrooms.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<SubjectDTO> getSubscribedSubjects(Long userId) {
        // Récupérer les identifiants des sujets auxquels l'utilisateur est abonné
        List<Long> subscribedSubjectIds = subscriptionRepository.findSubjectIdsByUserId(userId);

        // Récupérer les sujets correspondants
        List<Subject> subjects = subjectRepository.findAllById(subscribedSubjectIds);

        // Convertir les sujets en DTO
        return subjects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setDescription(subject.getDescription());
        dto.setCreatedAt(subject.getCreatedAt());
        return dto;
    }
}
