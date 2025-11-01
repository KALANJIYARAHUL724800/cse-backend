package com.example.cse_backend.services;

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


}
