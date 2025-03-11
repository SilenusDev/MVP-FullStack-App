package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.Subscription;
import com.openclassrooms.api.models.SubscriptionId;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.repositories.SubscriptionRepository;
import com.openclassrooms.api.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * Récupère les sujets auxquels l'utilisateur est abonné.
     */
    public List<SubjectDTO> getSubscribedSubjects(Long userId) {
        return subscriptionRepository.findByUserId(userId)
                .stream()
                .map(subscription -> SubjectDTO.fromEntity(subscription.getSubject()))
                .collect(Collectors.toList());
    }

    /**
     * Récupère la liste des sujets disponibles sous forme de DTO.
     */
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Abonne un utilisateur à un sujet.
     */
    public void subscribe(Long userId, Long subjectId) {
        // Vérifie si l'utilisateur existe
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        // Vérifie si le sujet existe
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found"));

        // Vérifie si l'utilisateur est déjà abonné au sujet
        boolean alreadySubscribed = subscriptionRepository.existsByUserIdAndSubjectId(userId, subjectId);
        if (alreadySubscribed) {
            throw new RuntimeException("User is already subscribed to this subject");
        }

        // Crée une nouvelle souscription
        Subscription subscription = new Subscription(user, subject);
        subscriptionRepository.save(subscription);
    }

    /**
     * Désabonne un utilisateur d'un sujet.
     */
    public void unsubscribe(Long userId, Long subjectId) {
        // Vérifie si l'utilisateur est abonné au sujet
        Subscription subscription = subscriptionRepository.findByUserIdAndSubjectId(userId, subjectId)
                .orElseThrow(() -> new RuntimeException("User is not subscribed to this subject"));

        // Supprime la souscription
        subscriptionRepository.delete(subscription);
    }
}
