package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;

public class MessageInfoDto {
    @NotBlank(message = "Email is required")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MessageInfoDto() {
    }
}
