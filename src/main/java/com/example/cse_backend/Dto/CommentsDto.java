package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentsDto {
    private Long id;

    @NotNull(message = "Likes cannot be null")
    private Long likes;

    private String comments;

    @NotNull(message = "Post ID is required")
    private Long postId;

    public CommentsDto() {
    }

    public CommentsDto(Long id, Long likes, String comments, Long postId) {
        this.id = id;
        this.likes = likes;
        this.comments = comments;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
