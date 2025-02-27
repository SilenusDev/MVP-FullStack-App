package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.ErrorResponse;
import com.openclassrooms.api.services.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Récupère les sujets auxquels l'utilisateur est abonné.
     */
    @Operation(summary = "Get subscribed subjects", description = "Returns a list of subjects the user is subscribed to")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of subscribed subjects",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/subscriptions")
    public List<SubjectDTO> getSubscribedSubjects(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return subscriptionService.getSubscribedSubjects(userId);
    }

    /**
     * S'abonner à un sujet.
     */
    @Operation(summary = "Subscribe to a subject", description = "Allows the user to subscribe to a specific subject")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully subscribed to the subject",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Subject not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/subscribe/{subjectId}")
    public void subscribe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long subjectId) {
        Long userId = Long.parseLong(userDetails.getUsername());
        subscriptionService.subscribe(userId, subjectId);
    }

    /**
     * Se désabonner d'un sujet.
     */
    @Operation(summary = "Unsubscribe from a subject", description = "Allows the user to unsubscribe from a specific subject")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully unsubscribed from the subject",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Subject not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/unsubscribe/{subjectId}")
    public void unsubscribe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long subjectId) {
        Long userId = Long.parseLong(userDetails.getUsername());
        subscriptionService.unsubscribe(userId, subjectId);
    }
}

