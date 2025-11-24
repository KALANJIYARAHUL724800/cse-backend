package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.ChangePasswordDto;
import com.example.cse_backend.Dto.MessageInfoDto;
import com.example.cse_backend.Dto.UserDto;
import com.example.cse_backend.Entity.UserEntity;
import com.example.cse_backend.config.JwtUtil;
import com.example.cse_backend.repository.LoginRepository;
import com.example.cse_backend.services.MessageInfoService;
import com.example.cse_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public UserService userService;
    @Autowired
    private MessageInfoService messageInfoService;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return userService.userRegister(data);
    }
    @PostMapping("/admin-register")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody UserDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return userService.adminRegister(data);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                if ("email".equals(field) || "password".equals(field)) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
            });
            if (errors.length() > 0) {
                return ResponseEntity.badRequest().body(errors.toString());
            }
        }
        var loginResponse = userService.userLogin(data);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            UserEntity user = loginRepository.findByEmail(data.getEmail());
            String token = jwtUtil.generateToken(data.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login success",
                    "token", token,
                    "email", data.getEmail(),
                    "userType", user.isUserType()
            ));
        } else {
            return loginResponse;
        }
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody UserDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                if ("email".equals(field)) {
                    errors.append(error.getDefaultMessage()).append("; ");
                } else if ("password".equals(field)) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
            });

            if (errors.length() > 0) {
                return ResponseEntity.badRequest().body(errors.toString());
            }
        }
        var loginResponse = userService.userLogin(data);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            UserEntity user = loginRepository.findByEmail(data.getEmail());
            String token = jwtUtil.generateToken(data.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login success",
                    "token", token,
                    "email", data.getEmail(),
                    "userType", user.isUserType()
            ));
        } else {
            return loginResponse;
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody MessageInfoDto data,BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error -> {
                errors.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errors.toString().trim());
        }
        try {
            var user = loginRepository.findByEmail(data.getEmail());
            if (user != null) {
                messageInfoService.sendEmail(data);
                return ResponseEntity.ok("Successfully message sent");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Email not found in our records!");
            }
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody ChangePasswordDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.badRequest().body(errors.toString());
        }
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match!");
        }
        var user = loginRepository.findByEmail(data.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found!");
        }
        return userService.updatePassword(data);
    }
    @GetMapping("/countAdmin")
    public ResponseEntity<?> countActiveUsers() {
        Long count = userService.countAdmin();

        if (count == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No active users found");
        }

        return ResponseEntity.ok(count);
    }
    @GetMapping("/countStudents")
    public ResponseEntity<?> countStudents() {
        Long count = userService.countStudents();
        return ResponseEntity.ok(count); // Always return count
    }
}