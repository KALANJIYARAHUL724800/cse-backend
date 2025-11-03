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
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "fk_course_content_course"))
    private CourseEntity courseId;
    @Lob
    @Column(name = "course_title")
    private String courseTitle;
    @Lob
    @Column(name = "what_you_will_learn")
    private String whatYouWillLearn;
    @Lob
    @Column(name = "who_can_join")
    private String whoCanJoin;
    @Lob
    @Column(name = "skills_you_will_gain")
    private String skillsYouWillGain;
    @Lob
    @Column(name = "course_topics")
    private String courseTopics;
    @Lob
    @Column(name = "career_opportunities")
    private String careerOpportunities;
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

    public CourseEntity getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseEntity courseId) {
        this.courseId = courseId;
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

    public CourseContentEntity(CourseEntity courseId, String courseTitle, String whatYouWillLearn, String whoCanJoin, String skillsYouWillGain, String courseTopics, String careerOpportunities, boolean active_flag) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.whatYouWillLearn = whatYouWillLearn;
        this.whoCanJoin = whoCanJoin;
        this.skillsYouWillGain = skillsYouWillGain;
        this.courseTopics = courseTopics;
        this.careerOpportunities = careerOpportunities;
        this.active_flag = active_flag;
    }

    public CourseContentEntity() {
    }
}
