package com.example.cse_backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "fees_enquiry",
        indexes = {
                @Index(name = "idx_phone", columnList = "phone"),
                @Index(name = "idx_course_name", columnList = "course_name")
        })
@Data
@NoArgsConstructor

public class FeesEnquiryFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "active_flag", nullable = false)
    private boolean active_flag = true;

    @Column(name = "enquiry_date", nullable = false)
    private LocalDate currentDate;

    @Column(name = "enquiry_time", nullable = false)
    private LocalTime currentTime;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
