package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;

public class PostDto {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "image is required")
    private String imageUrl;

    public PostDto(Long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public PostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
