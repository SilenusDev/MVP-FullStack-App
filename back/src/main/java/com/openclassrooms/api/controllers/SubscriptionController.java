package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.services.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<SubjectDTO>> getSubscribedSubjects(@PathVariable Long userId) {
        List<SubjectDTO> subjects = subscriptionService.getSubscribedSubjects(userId);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/subscribe/{userId}/{subjectId}")
    public ResponseEntity<Void> subscribe(@PathVariable Long userId, @PathVariable Long subjectId) {
        subscriptionService.subscribe(userId, subjectId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unsubscribe/{userId}/{subjectId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long userId, @PathVariable Long subjectId) {
        subscriptionService.unsubscribe(userId, subjectId);
        return ResponseEntity.ok().build();
    }
}
