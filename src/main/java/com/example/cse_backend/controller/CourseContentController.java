package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CourseContentDto;
import com.example.cse_backend.Entity.CourseContentEntity;
import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.services.CourseContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course-content")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertContent(@Valid @RequestBody CourseContentDto data, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return new ResponseEntity<>(courseContentService.insertContent(data), HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findCourseContent(@PathVariable Long id) {
        try {
            CourseContentDto dto = courseContentService.findCourseContent(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<?> getCourseContent(@PathVariable Long id) {
        try {
            List<CourseContentEntity> content = courseContentService.searchCourseContent(id);
            return ResponseEntity.ok(content);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/latest-course")
    public ResponseEntity<?> getLatestCourse() {
        CourseEntity latest = courseContentService.getLatestCourse();
        if (latest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found.");
        }
        return ResponseEntity.ok(latest);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContent(
            @PathVariable Long id,
            @Valid @RequestBody CourseContentDto data,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            CourseContentEntity updatedContent = courseContentService.updateContent(id, data);
            return ResponseEntity.ok(updatedContent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}