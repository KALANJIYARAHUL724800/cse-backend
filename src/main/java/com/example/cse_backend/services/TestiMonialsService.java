package com.example.cse_backend.services;
import java.util.Base64;
import java.util.stream.Collectors;
import com.example.cse_backend.Dto.TestiMonialsDto;
import com.example.cse_backend.Entity.TestiMonialsEntity;
import com.example.cse_backend.repository.TestiMonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TestiMonialsEntity update(Long id, TestiMonialsDto data) {
        TestiMonialsEntity obj = testiMonialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        obj.setName(data.getName());
        obj.setCourseName(data.getCourseName());
        obj.setText(data.getText());

        if (data.getImage() != null && data.getImage().length > 0) {
            obj.setImage(data.getImage());
        }

        return testiMonialsRepository.save(obj);
    }

    public TestiMonialsEntity find(Long id) {
        return testiMonialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));
    }
}
