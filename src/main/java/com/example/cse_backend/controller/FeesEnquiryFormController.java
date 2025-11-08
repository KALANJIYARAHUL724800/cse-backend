package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.FeesEnquiryFormDto;
import com.example.cse_backend.Entity.FeesEnquiryFormEntity;
import com.example.cse_backend.services.FeesEnquiryFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/enquiry")
public class FeesEnquiryFormController {
    @Autowired
    private FeesEnquiryFormService feesEnquiryFormService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertFeesFormEnquiry(@Valid @RequestBody FeesEnquiryFormDto data, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        FeesEnquiryFormEntity saved = feesEnquiryFormService.insertFeesEnquiry(data);
        return ResponseEntity.ok(saved);  // return saved entity
    }

    @GetMapping("/show")
    public ResponseEntity<?> showAllEnquiry()
    {
        return new ResponseEntity<>(feesEnquiryFormService.showAllEnquiry(),HttpStatus.OK);
    }
    @GetMapping("/between-dates")
    public List<FeesEnquiryFormEntity> getEnquiriesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return feesEnquiryFormService.getEnquiriesBetweenDates(startDate, endDate);
    }
    @GetMapping("/count")
    public Long getEnquiryCount(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return feesEnquiryFormService.getEnquiryCount(startDate, endDate);
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportEnquiriesExcel() throws Exception {
        ByteArrayInputStream in = feesEnquiryFormService.exportEnquiriesToExcel();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);

        String fileName = "enquiries_" + formattedDateTime + ".xlsx";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }
    @GetMapping("/export-between-dates")
    public ResponseEntity<byte[]> exportEnquiriesExcelBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) throws Exception {

        ByteArrayInputStream in = feesEnquiryFormService.exportEnquiriesToExcelBetweenDates(startDate, endDate);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        String fileName = "enquiries_" + formattedDateTime + ".xlsx";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }

}