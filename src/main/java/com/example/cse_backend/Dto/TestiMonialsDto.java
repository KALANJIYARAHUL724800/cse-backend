package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;

public class TestiMonialsDto {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "course name is required")
    private String courseName;
    @NotBlank(message = "content text is required")
    private String text;
    private byte[] image;
    private String imageBase64;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
