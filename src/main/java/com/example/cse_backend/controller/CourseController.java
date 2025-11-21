package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CourseDto;
import com.example.cse_backend.Entity.CourseEntity;
import com.example.cse_backend.repository.CourseRepository;
import com.example.cse_backend.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/all-courses")
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
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
    @GetMapping("/countcourse")
    public ResponseEntity<?> countCourses() {
        Long count = courseService.countCourse();

        if (count == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No courses found");
        }

        return ResponseEntity.ok(count);
    }
    @PutMapping("/courses/{id}/upload-pdf")
    public ResponseEntity<?> updateCoursePdf(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String msg = courseService.updateCoursePdf(id, file);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/courses/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        byte[] pdfData = course.getData();

        if (pdfData == null || pdfData.length == 0) {
            throw new RuntimeException("No PDF data found for course id " + id);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "course_" + id + ".pdf");

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }


}
