package com.example.cse_backend.services;

import com.example.cse_backend.Dto.ChangePasswordDto;
import com.example.cse_backend.Dto.MessageInfoDto;
import com.example.cse_backend.Dto.UserDto;
import com.example.cse_backend.Entity.UserEntity;
import com.example.cse_backend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    @Autowired
    public LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> userRegister(UserDto data)
    {
        if (loginRepository.existsByEmail(data.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }
        UserEntity user = new UserEntity();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(data.getConfirmPassword()));
        UserEntity savedUser = loginRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    public ResponseEntity<?> userLogin(UserDto data)
    {
        UserEntity user = loginRepository.findByEmail(data.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }

        return ResponseEntity.ok("Login success");
    }

    public ResponseEntity<?> updatePassword(ChangePasswordDto data) {
        UserEntity user = loginRepository.findByEmail(data.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found!");
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match!");
        }

        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(data.getConfirmPassword()));
        loginRepository.save(user);

        return ResponseEntity.ok("Password updated successfully!");
    }



}
