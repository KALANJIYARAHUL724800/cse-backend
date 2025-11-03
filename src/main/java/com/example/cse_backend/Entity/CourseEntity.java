package com.example.cse_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(
        name = "courses",
        indexes = {
                @Index(name = "idx_active_flag", columnList = "active_flag")
        }
)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Lob
    @Column(name = "course_content",nullable = false)
    private String courseContent;

    @Lob
    @Column(name = "logo_url",nullable = false,columnDefinition = "LONGTEXT")
    private String logoUrl;

    @Column(name = "course_month",nullable = false)
    private String month;

    @Column(name = "active_flag", nullable = false)
    private boolean active_flag = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public boolean isActive_flag() {
        return active_flag;
    }

    public void setActive_flag(boolean active_flag) {
        this.active_flag = active_flag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CourseEntity(Long id, String courseName, String courseContent, String logoUrl, String month, boolean active_flag, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseName = courseName;
        this.courseContent = courseContent;
        this.logoUrl = logoUrl;
        this.month = month;
        this.active_flag = active_flag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CourseEntity() {
    }
}
