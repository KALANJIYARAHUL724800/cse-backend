package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CourseContentDto;
import com.example.cse_backend.services.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course-content")  // 👈 unique base path
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertContent(@RequestBody CourseContentDto data) {
        return new ResponseEntity<>(courseContentService.insertContent(data), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCourseContent(@PathVariable Long id) {
        return new ResponseEntity<>(courseContentService.findCourseContent(id), HttpStatus.OK);
    }
}
