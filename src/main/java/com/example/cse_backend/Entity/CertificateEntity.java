package com.example.cse_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate", indexes = {
        @Index(name = "idx_active_flag", columnList = "active_flag")
})
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String certificateName;
    private String grade;
    private int enrollNumber;
    private LocalDate certificateDate;
    private String institutionName;
    private String location;
    private LocalDate joinDate;
    private LocalDate endDate;
    @Column(name = "active_flag", nullable = false)
    private boolean active_flag = true;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
