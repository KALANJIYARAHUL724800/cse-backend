package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.UserDto;
import com.example.cse_backend.Entity.UserEntity;
import com.example.cse_backend.config.JwtUtil;
import com.example.cse_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public UserService userService;

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
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                if (field.equals("email") || field.equals("password")) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
            });
            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors.toString());
            }
        }
        var loginResponse = userService.userLogin(data);

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            String token = jwtUtil.generateToken(data.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login success",
                    "token", token,
                    "email", data.getEmail()
            ));
        } else {
            return loginResponse;
        }
    }

}
