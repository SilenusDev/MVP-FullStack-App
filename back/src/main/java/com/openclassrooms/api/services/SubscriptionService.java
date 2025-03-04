package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.Subscription;
import com.openclassrooms.api.models.SubscriptionId;
import com.openclassrooms.api.repositories.SubjectRepository;
import com.openclassrooms.api.repositories.SubscriptionRepository;
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

    /**
     * Récupère les sujets auxquels l'utilisateur est abonné.
     */
    public List<SubjectDTO> getSubscribedSubjects(Long userId) {
        return subscriptionRepository.findByUserId(userId)
                .stream()
                .map(subscription -> new SubjectDTO(subscription.getSubject()))
                .collect(Collectors.toList());
    }

    /**
     * Récupère la liste des sujets disponibles sous forme de DTO.
     */
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Permet à un utilisateur de s'abonner à un sujet.
     */
    public void subscribe(Long userId, Long subjectId) {
        // Convertit subjectId en Long pour éviter l'erreur
        Subject subject = subjectRepository.findById(subjectId.longValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sujet non trouvé"));
    
        // Vérifie si l'utilisateur est déjà abonné
        if (subscriptionRepository.existsByUserIdAndSubjectId(userId, subjectId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur est déjà abonné");
        }
    
        Subscription subscription = new Subscription(new SubscriptionId(userId, subjectId), subject);
        subscriptionRepository.save(subscription);
    }
    

    /**
     * Permet à un utilisateur de se désabonner d'un sujet.
     */
    public void unsubscribe(Long userId, Long subjectId) {
        // Vérifie si l'utilisateur est bien abonné avant de supprimer
        if (!subscriptionRepository.existsByUserIdAndSubjectId(userId, subjectId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur n'est pas abonné à ce sujet");
        }

        subscriptionRepository.deleteByUserIdAndSubjectId(userId, subjectId);
    }

    /**
     * Vérifie si un utilisateur est abonné à un sujet.
     */
    public boolean isSubscribed(Long userId, Long subjectId) {
        return subscriptionRepository.existsByUserIdAndSubjectId(userId, subjectId);
    }
}
