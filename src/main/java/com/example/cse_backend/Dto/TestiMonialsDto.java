package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;

public class TestiMonialsDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "course name is required")
    private String courseName;

    @NotBlank(message = "text is required")
    private String text;

    @NotBlank(message = "place is required")
    private String place;

    private String imageUrl;
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TestiMonialsDto() {}

    public TestiMonialsDto(String name, String courseName, String text, String place,String imageUrl) {
        this.name = name;
        this.courseName = courseName;
        this.text = text;
        this.place = place;
        this.imageUrl = imageUrl;
    }
}
