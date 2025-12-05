package com.example.cse_backend.services;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import com.example.cse_backend.Dto.TestiMonialsDto;
import com.example.cse_backend.Entity.TestiMonialsEntity;
import com.example.cse_backend.repository.TestiMonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TestiMonialsService {
@Autowired
private TestiMonialsRepository testiMonialsRepository;

    public TestiMonialsEntity insert(TestiMonialsDto dto) {
        TestiMonialsEntity entity = new TestiMonialsEntity();
        entity.setName(dto.getName());
        entity.setEnrollno(dto.getEnrollno());
        entity.setCourseName(dto.getCourseName());
        entity.setText(dto.getText());
        entity.setPlace(dto.getPlace());
        entity.setImageUrl(dto.getImageUrl());
        return testiMonialsRepository.save(entity);
    }

    public List<TestiMonialsEntity> showAll() {
        return testiMonialsRepository.findAll();
    }
    public TestiMonialsEntity updateByEnrollno(Long enrollno, TestiMonialsDto data) throws IOException {
        TestiMonialsEntity obj = testiMonialsRepository.findByEnrollNo(enrollno)
                .orElseThrow(() -> new RuntimeException("Testimonial not found with enrollno: " + enrollno));

        obj.setName(data.getName());
        obj.setEnrollno(data.getEnrollno());
        obj.setCourseName(data.getCourseName());
        obj.setText(data.getText());
        obj.setPlace(data.getPlace());
        obj.setImageUrl(data.getImageUrl());

        return testiMonialsRepository.save(obj);
    }

    public TestiMonialsEntity find(Long id) {
        return testiMonialsRepository.findByEnrollNo(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));
    }
}
