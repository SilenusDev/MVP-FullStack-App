package com.openclassrooms.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.Subject;
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
        List<Subject> subjects = subjectRepository.findAllById(subscribedSubjectIds); // OK

        // Convertir les sujets en DTO
        return subjects.stream()
                .map(SubjectDTO::new) // Utilisation du constructeur de SubjectDTO
                .collect(Collectors.toList());
    }
}
