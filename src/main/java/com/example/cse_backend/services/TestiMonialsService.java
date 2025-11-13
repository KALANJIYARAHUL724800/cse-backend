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

    public TestiMonialsEntity insert(TestiMonialsDto dto, byte[] fileBytes) {
        TestiMonialsEntity entity = new TestiMonialsEntity();
        entity.setName(dto.getName());
        entity.setCourseName(dto.getCourseName());
        entity.setText(dto.getText());
        entity.setPlace(dto.getPlace());
        entity.setImageUrl(dto.getImageUrl());
        if (fileBytes != null && fileBytes.length > 0) {
            entity.setImage(fileBytes);
        } else {
            entity.setImage(null);
        }
        return testiMonialsRepository.save(entity);
    }

    public List<TestiMonialsEntity> showAll() {
        return testiMonialsRepository.findAll();
    }
    public TestiMonialsEntity update(Long id, TestiMonialsDto data, MultipartFile image) throws IOException {
        TestiMonialsEntity obj = testiMonialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        obj.setName(data.getName());
        obj.setCourseName(data.getCourseName());
        obj.setText(data.getText());
        obj.setPlace(data.getPlace());
        obj.setImageUrl(data.getImageUrl());
        if (image != null && !image.isEmpty()) {
            obj.setImage(image.getBytes());
        }
        // else keep old image as-is

        return testiMonialsRepository.save(obj);
    }

    public TestiMonialsEntity find(Long id) {
        return testiMonialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));
    }
}
