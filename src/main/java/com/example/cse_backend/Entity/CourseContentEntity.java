package com.example.cse_backend.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "course_content",
        indexes = {
                @Index(name = "course_id", columnList = "course_id")
        }
)
public class CourseContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Lob
    @Column(name = "course_title", columnDefinition = "LONGTEXT")
    private String courseTitle;

    @Lob
    @Column(name = "what_you_will_learn", columnDefinition = "LONGTEXT")
    private String whatYouWillLearn;

    @Lob
    @Column(name = "who_can_join", columnDefinition = "LONGTEXT")
    private String whoCanJoin;

    @Lob
    @Column(name = "skills_you_will_gain", columnDefinition = "LONGTEXT")
    private String skillsYouWillGain;

    @Lob
    @Column(name = "course_topics", columnDefinition = "LONGTEXT")
    private String courseTopics;

    @Lob
    @Column(name = "career_opportunities", columnDefinition = "LONGTEXT")
    private String careerOpportunities;

    @Column(name = "active_flag", nullable = false)
    private boolean active_flag = true;

    @Lob
    @Column(name = "logo_url", columnDefinition = "LONGTEXT")
    private String logoUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getWhatYouWillLearn() {
        return whatYouWillLearn;
    }

    public void setWhatYouWillLearn(String whatYouWillLearn) {
        this.whatYouWillLearn = whatYouWillLearn;
    }

    public String getWhoCanJoin() {
        return whoCanJoin;
    }

    public void setWhoCanJoin(String whoCanJoin) {
        this.whoCanJoin = whoCanJoin;
    }

    public String getSkillsYouWillGain() {
        return skillsYouWillGain;
    }

    public void setSkillsYouWillGain(String skillsYouWillGain) {
        this.skillsYouWillGain = skillsYouWillGain;
    }

    public String getCourseTopics() {
        return courseTopics;
    }

    public void setCourseTopics(String courseTopics) {
        this.courseTopics = courseTopics;
    }

    public String getCareerOpportunities() {
        return careerOpportunities;
    }

    public void setCareerOpportunities(String careerOpportunities) {
        this.careerOpportunities = careerOpportunities;
    }

    public boolean isActive_flag() {
        return active_flag;
    }

    public void setActive_flag(boolean active_flag) {
        this.active_flag = active_flag;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    // --- Constructors ---
    public CourseContentEntity() {}

    public CourseContentEntity(CourseEntity course, String courseTitle, String whatYouWillLearn,
                               String whoCanJoin, String skillsYouWillGain, String courseTopics,
                               String careerOpportunities, boolean active_flag) {
        this.course = course;
        this.courseTitle = courseTitle;
        this.whatYouWillLearn = whatYouWillLearn;
        this.whoCanJoin = whoCanJoin;
        this.skillsYouWillGain = skillsYouWillGain;
        this.courseTopics = courseTopics;
        this.careerOpportunities = careerOpportunities;
        this.active_flag = active_flag;
    }
}
