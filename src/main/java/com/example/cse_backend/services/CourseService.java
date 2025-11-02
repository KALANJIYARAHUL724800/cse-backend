package com.example.cse_backend.services;

import com.example.cse_backend.Dto.CourseDto;
import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> getAllCourses()
    {
        return courseRepository.findAll();
    }
    public CourseEntity findCourse(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
    public CourseEntity addCourse(CourseDto data)
    {
        CourseEntity obj = new CourseEntity();
        obj.setCourseName(data.getCourseName());
        obj.setCourseContent(data.getCourseContent());
        obj.setMonth(data.getMonth());
        obj.setLogoUrl(data.getLogoUrl());
        return courseRepository.save(obj);
    }
    public CourseEntity updateCourse(Long id,CourseDto data)
    {
        CourseEntity obj = courseRepository.findById(id).orElse(null);
        obj.setCourseName(data.getCourseName());
        obj.setCourseContent(data.getCourseContent());
        obj.setMonth(data.getMonth());
        obj.setLogoUrl(data.getLogoUrl());
        return courseRepository.save(obj);
    }
    public List<CourseEntity> searchCourseLike(String value) {
        return courseRepository.searchByName(value);
    }
}
