package com.example.cse_backend.services;

import com.example.cse_backend.Dto.CourseContentDto;
import com.example.cse_backend.Entity.CourseContentEntity;
import com.example.cse_backend.repository.CourseContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentService {
    @Autowired
    private CourseContentRepository courseContentRepository;
    public CourseContentEntity insertContent(CourseContentDto data)
    {
        CourseContentEntity content = new CourseContentEntity();
        content.setId(data.getId());
        content.setCourseId(data.getCourseId());
        content.setCourseTitle(data.getCourseTitle());
        content.setCourseTopics(data.getCourseTopics());
        content.setCareerOpportunities(data.getCareerOpportunities());
        content.setSkillsYouWillGain(data.getSkillsYouWillGain());
        content.setWhatYouWillLearn(data.getWhatYouWillLearn());
        content.setWhoCanJoin(data.getWhoCanJoin());
        return courseContentRepository.save(content);
    }
    public CourseContentEntity findCourseContent(Long id) {
        return courseContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " ));
    }

}
