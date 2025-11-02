package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;

public class CourseDto {

    private Long id;
    @NotBlank(message = "course name is required")
    private String courseName;
    @NotBlank(message = "course content is required")
    private String courseContent;
    @NotBlank(message = "image url is required")
    private String logoUrl;
    @NotBlank(message = "month is required")
    private String month;

    public CourseDto(Long id, String courseName, String courseContent, String logoUrl, String month) {
        this.id = id;
        this.courseName = courseName;
        this.courseContent = courseContent;
        this.logoUrl = logoUrl;
        this.month = month;
    }

    public CourseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
