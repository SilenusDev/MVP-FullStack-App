package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.services.PostService;
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

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // @Operation(summary = "Get subscribed posts", description = "Returns a list of posts from users the current user is subscribed to")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Posts retrieved successfully",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class, type = "array"))),
    //     @ApiResponse(responseCode = "401", description = "Unauthorized",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
    //     @ApiResponse(responseCode = "500", description = "Internal server error",
    //         content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    // })
    // @GetMapping("/subscribed/{userId}")
    // public List<PostDTO> getSubscribedPosts(@PathVariable Long userId) {
    //     return postService.getSubscribedPosts(userId);
    // }

    @Operation(summary = "Get a post by ID", description = "Returns a post by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Post not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }


    @Operation(summary = "Create a new post", description = "Creates a new post and returns the created post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public PostDTO createPost(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PostDTO.class),
            examples = @ExampleObject(
                name = "Post Example",
                value = "{\"title\": \"My First Post\", \"content\": \"This is the content of my first post.\", \"subjectId\": 1, \"authorId\": 1}"
            )
        )
    ) PostDTO postDTO) {
        return postService.createPost(postDTO);
    }
}