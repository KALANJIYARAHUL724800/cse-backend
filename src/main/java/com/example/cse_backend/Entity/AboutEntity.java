package com.example.cse_backend.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import com.vladmihalcea.hibernate.type.json.JsonType;

@Entity
@Table(name = "about", indexes = {
        @Index(name = "idx_active_flag", columnList = "active_flag")
})
public class AboutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "urls", nullable = true)
    private String urls;
    @JsonProperty("content")
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String content;

    @Column(name = "active_flag", nullable = false)
    private boolean active_flag = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
