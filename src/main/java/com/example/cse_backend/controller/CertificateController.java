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
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

            for (FieldError error : bindingResult.getFieldErrors()) {
                String field = error.getField();
                errorsMap.put(field, error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorsMap);
        }
        CertificateEntity result = certificateService.uploadCertificateData(certificateDto);
        if (result == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Enroll Number already exists");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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
