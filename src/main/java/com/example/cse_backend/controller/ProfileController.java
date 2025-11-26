package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.ProfileDto;
import com.example.cse_backend.Entity.ProfileEntity;
import com.example.cse_backend.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody ProfileDto data, BindingResult result)
    {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return new ResponseEntity<>(profileService.insert(data), HttpStatus.OK);
    }
    @GetMapping("/show")
    public List<ProfileEntity> showAll()
    {
        return profileService.showAll();
    }
}
