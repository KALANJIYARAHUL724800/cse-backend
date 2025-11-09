package com.example.cse_backend.services;

import com.example.cse_backend.Dto.CourseContentDto;
import com.example.cse_backend.Entity.CourseContentEntity;
import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.repository.CourseContentRepository;
import com.example.cse_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentService {

    @Autowired
    private CourseContentRepository courseContentRepository;

    @Autowired
    private CourseRepository courseRepository;
    public CourseContentEntity insertContent(CourseContentDto data) {
        CourseEntity course = courseRepository.findById(data.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + data.getCourseId()));
        CourseContentEntity content = new CourseContentEntity();
        content.setCourse(course);
        content.setLogoUrl(data.getLogoUrl());
        content.setCourseTitle(data.getCourseTitle());
        content.setCourseTopics(data.getCourseTopics());
        content.setCareerOpportunities(data.getCareerOpportunities());
        content.setSkillsYouWillGain(data.getSkillsYouWillGain());
        content.setWhatYouWillLearn(data.getWhatYouWillLearn());
        content.setWhoCanJoin(data.getWhoCanJoin());
        return courseContentRepository.save(content);
    }
    public CourseContentDto findCourseContent(Long id) {
        return courseContentRepository.findById(id)
                .map(entity -> {
                    CourseContentDto dto = new CourseContentDto();
                    dto.setId(entity.getId());
                    dto.setCourseTitle(entity.getCourse().getCourseName());
                    dto.setWhatYouWillLearn(entity.getWhatYouWillLearn());
                    dto.setWhoCanJoin(entity.getWhoCanJoin());
                    dto.setSkillsYouWillGain(entity.getSkillsYouWillGain());
                    dto.setCourseTopics(entity.getCourseTopics());
                    dto.setCareerOpportunities(entity.getCareerOpportunities());
                    dto.setCourseId(entity.getCourse().getId());
                    dto.setLogoUrl(entity.getLogoUrl());
                    return dto;
                })
                .orElse(null);
    }
    public List<CourseContentEntity> searchCourseContent(Long courseId) {
        return courseContentRepository.findByCourse_Id(courseId);
    }
    public CourseEntity getLatestCourse() {
        return courseRepository.findLatestCourse();
    }
    public CourseContentEntity updateContent(Long id, CourseContentDto data) {
        CourseContentEntity existingContent = courseContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with ID: " + id));
        CourseEntity course = courseRepository.findById(data.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + data.getCourseId()));
        existingContent.setCourse(course);
        existingContent.setLogoUrl(data.getLogoUrl());
        existingContent.setCourseTitle(data.getCourseTitle());
        existingContent.setCourseTopics(data.getCourseTopics());
        existingContent.setCareerOpportunities(data.getCareerOpportunities());
        existingContent.setSkillsYouWillGain(data.getSkillsYouWillGain());
        existingContent.setWhatYouWillLearn(data.getWhatYouWillLearn());
        existingContent.setWhoCanJoin(data.getWhoCanJoin());
        return courseContentRepository.save(existingContent);
    }

}
