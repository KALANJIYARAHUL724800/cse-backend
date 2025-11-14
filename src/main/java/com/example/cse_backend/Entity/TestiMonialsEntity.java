package com.example.cse_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "testimonials",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_course_name", columnList = "course_name"),
                @Index(name = "idx_active_flag", columnList = "active_flag")
        }
)
public class TestiMonialsEntity {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "enroll_no",nullable = false)
    private Long enrollno;

    @Column(name = "course_name",nullable = false)
    private String courseName;

    @Lob
    @Column(name = "text",nullable = false)
    private String text;

    @JsonIgnore
    @Lob
    @Column(name = "image",columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "place",nullable = false)
    private String place;

    @Column(name = "active_flag",nullable = false)
    private boolean active_flag = true;

    @Lob
    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getEnrollno() {
        return enrollno;
    }

    public void setEnrollno(Long enrollno) {
        this.enrollno = enrollno;
    }

    public TestiMonialsEntity(Long id, String name,Long enrollno, String courseName, String text, byte[] image, String place, boolean active_flag, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.enrollno = enrollno;
        this.courseName = courseName;
        this.text = text;
        this.image = image;
        this.place = place;
        this.active_flag = active_flag;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public TestiMonialsEntity(Long id, String name, String courseName, String text, byte[] image, String place) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.text = text;
        this.image = image;
        this.place = place;
    }

    public TestiMonialsEntity(Long id, String name, String courseName, String text, byte[] image, String place, boolean active_flag, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.text = text;
        this.image = image;
        this.place = place;
        this.active_flag = active_flag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TestiMonialsEntity(Long id, String name, String courseName, String text, byte[] image) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.text = text;
        this.image = image;
    }

    public TestiMonialsEntity() {
    }
}
