package com.example.cse_backend.services;

import com.example.cse_backend.Dto.FeesEnquiryFormDto;
import com.example.cse_backend.Entity.FeesEnquiryFormEntity;
import com.example.cse_backend.repository.FeesEnquiryFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class FeesEnquiryFormService {
    @Autowired
    private FeesEnquiryFormRepository feesEnquiryFormRepository;

    public FeesEnquiryFormEntity insertFeesEnquiry(FeesEnquiryFormDto dto) {
        FeesEnquiryFormEntity entity = new FeesEnquiryFormEntity();
        entity.setCourseName(dto.getCourseTitle() == null ? "" : dto.getCourseTitle());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setCurrentDate(LocalDate.now());
        entity.setCurrentTime(LocalTime.now());
        entity.setActive_flag(true);
        return feesEnquiryFormRepository.save(entity);
    }
    public FeesEnquiryFormEntity insertFeesEnquiryTemp(FeesEnquiryFormDto dto) {
        FeesEnquiryFormEntity entity = new FeesEnquiryFormEntity();
        entity.setCourseName("No course choose direct enquiry");
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setCurrentDate(LocalDate.now());
        entity.setCurrentTime(LocalTime.now());
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
    public ByteArrayInputStream exportEnquiriesToExcel() throws Exception {
        List<FeesEnquiryFormEntity> enquiries = feesEnquiryFormRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Enquiries");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Phone");
        header.createCell(3).setCellValue("Course Name");
        header.createCell(4).setCellValue("Current Date");
        header.createCell(5).setCellValue("Current Time");
        int rowIdx = 1;
        for (FeesEnquiryFormEntity e : enquiries) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(e.getId());
            row.createCell(1).setCellValue(e.getName());
            row.createCell(2).setCellValue(e.getPhone());
            row.createCell(3).setCellValue(e.getCourseName());
            row.createCell(4).setCellValue(e.getCurrentDate().toString());
            row.createCell(5).setCellValue(e.getCurrentTime().toString());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
    public ByteArrayInputStream exportEnquiriesToExcelBetweenDates(LocalDate startDate, LocalDate endDate) throws Exception {
        List<FeesEnquiryFormEntity> enquiries = feesEnquiryFormRepository.findByCurrentDateBetween(startDate, endDate);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Enquiries");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Phone");
        header.createCell(3).setCellValue("Course Name");
        header.createCell(4).setCellValue("Current Date");
        header.createCell(5).setCellValue("Current Time");
        int rowIdx = 1;
        for (FeesEnquiryFormEntity e : enquiries) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(e.getId());
            row.createCell(1).setCellValue(e.getName());
            row.createCell(2).setCellValue(e.getPhone());
            row.createCell(3).setCellValue(e.getCourseName());
            row.createCell(4).setCellValue(e.getCurrentDate().toString());
            row.createCell(5).setCellValue(e.getCurrentTime().toString());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

}
