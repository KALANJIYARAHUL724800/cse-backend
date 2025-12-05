package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.TestiMonialsDto;
import com.example.cse_backend.Entity.TestiMonialsEntity;
import com.example.cse_backend.services.TestiMonialsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/testimonials")
public class TestiMonialsController {
    @Autowired
    private TestiMonialsService testiMonialsService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(
            @Valid @ModelAttribute TestiMonialsDto dto,
            BindingResult result,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Object saved = testiMonialsService.insert(dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("image", "File upload failed: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
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
            @RequestBody TestiMonialsDto dto
    ) {
        try {
            TestiMonialsEntity updated = testiMonialsService.updateByEnrollno(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed: " + e.getMessage());
        }
    }

}
