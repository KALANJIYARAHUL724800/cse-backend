package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CourseDto;
import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
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
    @PostMapping("/addcourse")
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errors.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseService.addCourse(data), HttpStatus.OK);
    }
    @PutMapping("/updatecourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,@Valid @RequestBody CourseDto data, BindingResult result)
    {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errors.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseService.updateCourse(id,data),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchCourses(@RequestParam String value) {
        List<CourseEntity> courses = courseService.searchCourseLike(value);
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No courses found for: " + value);
        }
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/latest-course")
    public ResponseEntity<CourseEntity> getLatestCourse() {
        CourseEntity course = courseService.getCourseWithMaxId();
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
