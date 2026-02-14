package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CertificateDto;
import com.example.cse_backend.Entity.CertificateEntity;
import com.example.cse_backend.services.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/certificate")
@CrossOrigin("*")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertCertificateDate(@RequestBody @Valid  CertificateDto certificateDto, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {

            // Prepare a map with default empty strings for all fields
            Map<String, String> errorsMap = new LinkedHashMap<>();
            errorsMap.put("name", "");
            errorsMap.put("certificateName", "");
            errorsMap.put("grade", "");
            errorsMap.put("certificateDate", "");
            errorsMap.put("joinDate", "");
            errorsMap.put("endDate", "");
            errorsMap.put("institutionName", "");
            errorsMap.put("location", "");
            errorsMap.put("enrollNumber", "");

            // Fill the map with actual error messages
            for (FieldError error : bindingResult.getFieldErrors()) {
                String field = error.getField();
                errorsMap.put(field, error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorsMap);
        }
        return new ResponseEntity<>(certificateService.uploadCertificateData(certificateDto), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findByEnrollNo(@RequestParam("enrollNo") Integer enrollNo) {

        List<CertificateEntity> res = certificateService.findByEnrollNo(enrollNo);

        if (res == null || res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No certificate found");
        }

        return ResponseEntity.ok(res);
    }

}
