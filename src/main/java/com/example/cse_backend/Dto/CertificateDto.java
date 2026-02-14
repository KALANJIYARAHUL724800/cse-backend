package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDto {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "certificate name is required")
    private String certificateName;
    @NotBlank(message = "grade name is required")
    private String grade;
    @NotNull(message = "enroll number is required")
    private Integer enrollNumber;
    @NotNull(message = "certificate date is required")
    private LocalDate certificateDate;
    private String institutionName;
    @NotBlank(message = "location is requrired")
    private String location;
    @NotNull(message = "join date date is required")
    private LocalDate joinDate;
    @NotNull(message = "end date date is required")
    private LocalDate endDate;
    private boolean active_flag = true;
}
