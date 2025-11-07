package com.example.cse_backend.services;

import com.example.cse_backend.Dto.FeesEnquiryFormDto;
import com.example.cse_backend.Entity.FeesEnquiryFormEntity;
import com.example.cse_backend.repository.FeesEnquiryFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FeesEnquiryFormService {
    @Autowired
    private FeesEnquiryFormRepository feesEnquiryFormRepository;

    public FeesEnquiryFormEntity insertFeesEnquiry(FeesEnquiryFormDto dto) {
        FeesEnquiryFormEntity entity = new FeesEnquiryFormEntity();
        entity.setCourseName(dto.getCourseTitle());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setCurrentDate(LocalDate.now());   // set current date
        entity.setCurrentTime(LocalTime.now());   // set current time
        entity.setActive_flag(true);
        return feesEnquiryFormRepository.save(entity);
    }

    public List<FeesEnquiryFormEntity> showAllEnquiry()
    {
        return feesEnquiryFormRepository.findAll();
    }
    public List<FeesEnquiryFormEntity> getEnquiriesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return feesEnquiryFormRepository.findByCurrentDateBetween(startDate, endDate);
    }
    public Long getEnquiryCount(String startDate, String endDate) {
        return feesEnquiryFormRepository.countEnquiriesBetweenDates(startDate, endDate);
    }
}
