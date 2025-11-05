package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
public class FeesEnquiryFormDto {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "phone number is required")
    private String phone;
    private String courseName;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private String courseTitle;

    public FeesEnquiryFormDto() {
    }
}
