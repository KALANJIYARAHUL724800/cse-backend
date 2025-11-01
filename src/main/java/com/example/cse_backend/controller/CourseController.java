package com.example.cse_backend.controller;

import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/all-courses")
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();  // this fetches all records
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<CourseEntity> findCourse(@PathVariable Long id) {
        CourseEntity course = courseService.findCourse(id);
        if(course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
