package com.example.cse_backend.controller;

import com.example.cse_backend.services.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AboutController {
    @Autowired
    private AboutService aboutService;
    @GetMapping("/about-content")
    public ResponseEntity<?> getAllContent()
    {
        return new ResponseEntity<>(aboutService.allContent(), HttpStatus.OK);
    }
}
