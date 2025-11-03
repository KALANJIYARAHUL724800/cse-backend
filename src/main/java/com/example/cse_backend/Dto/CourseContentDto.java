package com.example.cse_backend.Dto;

import com.example.cse_backend.Entity.CourseEntity;

public class CourseContentDto {
    private Long id;
    private Long courseId;
    private String courseTitle;
    private String whatYouWillLearn;
    private String whoCanJoin;
    private String skillsYouWillGain;
    private String courseTopics;
    private String careerOpportunities;
    private String logoUrl;
    private CourseEntity course;

    public CourseEntity getCourse() { return course; }
    public void setCourse(CourseEntity course) { this.course = course; }
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
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

    public CourseContentDto() {
    }

    public CourseContentDto(Long courseId, String courseTitle, String whatYouWillLearn, String whoCanJoin, String skillsYouWillGain, String courseTopics, String careerOpportunities) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.whatYouWillLearn = whatYouWillLearn;
        this.whoCanJoin = whoCanJoin;
        this.skillsYouWillGain = skillsYouWillGain;
        this.courseTopics = courseTopics;
        this.careerOpportunities = careerOpportunities;
    }
}
