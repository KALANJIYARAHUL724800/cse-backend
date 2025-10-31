
        package com.example.cse_backend.Entity;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.vladmihalcea.hibernate.type.json.JsonType;
        import jakarta.persistence.*;
        import org.hibernate.annotations.Type;
        import org.hibernate.annotations.UpdateTimestamp;
        import java.time.LocalDateTime;

        @Entity
        @Table(
                name = "course_details",
                indexes = {
                        @Index(name = "course_id", columnList = "course_id")
                }
        )
        public class CourseDetailsEntity {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "fk_course_details_course"))
            private CourseEntity course;

            @JsonProperty("content_details")
            @Type(JsonType.class)
            @Column(columnDefinition = "json")
            private String courseContent;

            @Column(name = "active_flag", nullable = false)
            private boolean active_flag = true;

            @Column(name = "created_at", updatable = false)
            private LocalDateTime createdAt;

            @UpdateTimestamp
            @Column(name = "updated_at")
            private LocalDateTime updatedAt;
        }
