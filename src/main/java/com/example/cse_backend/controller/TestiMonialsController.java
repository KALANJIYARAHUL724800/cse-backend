package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.TestiMonialsDto;
import com.example.cse_backend.services.TestiMonialsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("api/testimonials")
public class TestiMonialsController {
    @Autowired
    private TestiMonialsService testiMonialsService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(
            @RequestParam("name") String name,
            @RequestParam("courseName") String courseName,
            @RequestParam("text") String text,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            byte[] fileBytes = null;
            if (image != null && !image.isEmpty()) {
                fileBytes = image.getBytes();
            }

            TestiMonialsDto dto = new TestiMonialsDto();
            dto.setName(name);
            dto.setCourseName(courseName);
            dto.setText(text);

            return new ResponseEntity<>(testiMonialsService.insert(dto, fileBytes), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> showAll()
    {
        return new ResponseEntity<>(testiMonialsService.showAll(),HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(testiMonialsService.find(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Testimonial not found: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TestiMonialsDto data,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        return new ResponseEntity<>(testiMonialsService.update(id, data), HttpStatus.OK);
    }

}
