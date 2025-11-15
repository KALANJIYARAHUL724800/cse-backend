package com.example.cse_backend.Dto;

public class PostCommentsResponseDto {
    private String title;
    private String imageUrl;
    private Long likes;
    private String comments;

    public PostCommentsResponseDto(String title, String imageUrl, Long likes, String comments) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.comments = comments;
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
