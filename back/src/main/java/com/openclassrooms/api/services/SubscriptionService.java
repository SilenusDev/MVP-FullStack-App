package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.Subscription;
import com.openclassrooms.api.models.SubscriptionId;
import com.openclassrooms.api.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<SubjectDTO> getSubscribedSubjects(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId.intValue());
        return subscriptions.stream()
                .map(subscription -> new SubjectDTO(subscription.getSubject()))
                .collect(Collectors.toList());
    }

    public void subscribe(Long userId, Long subjectId) {
        Subscription subscription = new Subscription();
        SubscriptionId id = new SubscriptionId();
        id.setUserId(userId.intValue());
        id.setSubjectId(subjectId.intValue());
        subscription.setId(id);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(Long userId, Long subjectId) {
        subscriptionRepository.deleteByUserIdAndSubjectId(userId.intValue(), subjectId.intValue());
    }

    public boolean isSubscribed(Long userId, Long subjectId) {
        return subscriptionRepository.existsByUserIdAndSubjectId(userId.intValue(), subjectId.intValue());
    }
}
