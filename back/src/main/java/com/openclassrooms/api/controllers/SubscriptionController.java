package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.dto.SubscriptionRequestDTO;
import com.openclassrooms.api.models.ErrorResponse;
import com.openclassrooms.api.services.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // /**
    //  * Récupère les sujets auxquels l'utilisateur est abonné.
    //  */
    // @Operation(summary = "Get subscribed subjects", description = "Returns a list of subjects the user is subscribed to")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Successfully retrieved list of subscribed subjects",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDTO.class))),
    //     @ApiResponse(responseCode = "401", description = "Unauthorized",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    //     @ApiResponse(responseCode = "500", description = "Internal server error",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    // })
    // @GetMapping("/subscriptions")
    // public List<SubjectDTO> getSubscribedSubjects(@AuthenticationPrincipal UserDetails userDetails) {
    //     Long userId = Long.parseLong(userDetails.getUsername());
    //     return subscriptionService.getSubscribedSubjects(userId);
    // }

    /**
     * Abonne un utilisateur à un sujet.
     */
    @Operation(summary = "Subscribe to a subject", description = "Subscribes a user to a subject by user ID and subject ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully subscribed to the subject"),
        @ApiResponse(responseCode = "400", description = "User is already subscribed to the subject",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody SubscriptionRequestDTO request) {
        try {
            subscriptionService.subscribe(request.getUserId(), request.getSubjectId());
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Subscription successful");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("message", "User is already subscribed to this subject");
            }});
        }
    }

    /**
     * Désabonne un utilisateur d'un sujet.
     */
    @Operation(summary = "Unsubscribe from a subject", description = "Unsubscribes a user from a subject by user ID and subject ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully unsubscribed from the subject"),
        @ApiResponse(responseCode = "400", description = "User is not subscribed to the subject",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Object> unsubscribe(@RequestBody SubscriptionRequestDTO request) {
        try {
            subscriptionService.unsubscribe(request.getUserId(), request.getSubjectId());
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Unsubscription successful");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {{
                put("message", "User is not subscribed to this subject");
            }});
        }
    }
}

