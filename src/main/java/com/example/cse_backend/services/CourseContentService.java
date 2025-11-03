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
    public CourseContentEntity insertContent(CourseContentDto data)
    {
        CourseEntity course = courseRepository.findById(data.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + data.getCourseId()));

        CourseContentEntity content = new CourseContentEntity();
        content.setCourseId(course);
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
        CourseContentEntity entity = courseContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course content not found with ID: " + id));

        CourseContentDto dto = new CourseContentDto();
        dto.setId(entity.getId());
        dto.setCourseTitle(entity.getCourseTitle());
        dto.setWhatYouWillLearn(entity.getWhatYouWillLearn());
        dto.setWhoCanJoin(entity.getWhoCanJoin());
        dto.setSkillsYouWillGain(entity.getSkillsYouWillGain());
        dto.setCourseTopics(entity.getCourseTopics());
        dto.setCareerOpportunities(entity.getCareerOpportunities());
        dto.setCourse(entity.getCourseId());
        return dto;
    }
    public List<CourseContentEntity> searchCourseContent(Long courseId) {
        return courseContentRepository.findByCourseEntityId(courseId);
    }


}
