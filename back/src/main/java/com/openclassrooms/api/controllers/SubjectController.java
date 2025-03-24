package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.ErrorResponse;
import com.openclassrooms.api.services.SubjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * Récupère tous les sujets.
     */
    @Operation(summary = "Get all subjects", description = "Returns a list of all available subjects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of subjects",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

}

