package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDto {
    private Long id;
    @NotBlank(message = "course name is required")
    private String course;
    @NotBlank(message = "date is required")
    private String date;
    @NotBlank(message = "time is required")
    private String time;
    private Boolean activeFlag = true;
}
