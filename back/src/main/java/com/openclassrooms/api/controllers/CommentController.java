package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.CommentDTO;
import com.openclassrooms.api.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Create a new comment", description = "Creates a new comment and returns the created comment")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Comment created successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDTO.class))),
    @ApiResponse(responseCode = "400", description = "Invalid input",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
    @ApiResponse(responseCode = "500", description = "Internal server error",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public CommentDTO createComment(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CommentDTO.class),
            examples = @ExampleObject(
                name = "Comment Example",
                value = "{\"content\": \"This is a great post!\", \"post\": 1, \"author\": 1}"
            )
        )
    ) CommentDTO commentDTO, @AuthenticationPrincipal UserDetails userDetails) {
        // Vous pouvez toujours utiliser userDetails pour vérifier ou compléter les informations si nécessaire
        return commentService.createComment(commentDTO);
    }
}