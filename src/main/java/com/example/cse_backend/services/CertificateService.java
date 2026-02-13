package com.example.cse_backend.services;

import com.example.cse_backend.Dto.CertificateDto;
import com.example.cse_backend.Entity.CertificateEntity;
import com.example.cse_backend.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    public CertificateEntity uploadCertificateData(CertificateDto data)
    {
        CertificateEntity obj = new CertificateEntity();
        obj.setName(data.getName());
        obj.setEnrollNumber(data.getEnrollNumber());
        obj.setCertificateName(data.getCertificateName());
        obj.setCertificateDate(data.getCertificateDate());
        obj.setLocation(data.getLocation());
        obj.setInstitutionName(data.getInstitutionName());
        obj.setJoinDate(data.getJoinDate());
        obj.setEndDate(data.getEndDate());
        obj.setGrade(data.getGrade());
        return certificateRepository.save(obj);
    }
}
