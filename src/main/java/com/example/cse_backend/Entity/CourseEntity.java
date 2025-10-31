package com.example.cse_backend.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
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
    @Column(name = "logo_url",nullable = false)
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
}
