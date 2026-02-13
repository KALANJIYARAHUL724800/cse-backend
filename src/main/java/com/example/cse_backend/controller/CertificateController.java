package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.CertificateDto;
import com.example.cse_backend.services.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertCertificateDate(@RequestBody @Valid  CertificateDto certificateDto, BindingResult bindingResult)
    {
        ArrayList list = new ArrayList();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                String errorMessage = error.getDefaultMessage();
                list.add(errorMessage);
            }
            return ResponseEntity.badRequest().body(list);
        }
        return new ResponseEntity<>(certificateService.uploadCertificateData(certificateDto), HttpStatus.OK);
    }

}
