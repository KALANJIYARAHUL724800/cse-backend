package com.example.cse_backend.services;

import com.example.cse_backend.Dto.BatchDto;
import com.example.cse_backend.Entity.BatchEntity;
import com.example.cse_backend.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    // Save or Update Batch
    public BatchDto saveBatch(BatchDto batchDto) {
        BatchEntity entity = new BatchEntity();

        if (batchDto.getId() != null) {
            entity = batchRepository.findById(batchDto.getId()).orElse(new BatchEntity());
        }

        entity.setCourse(batchDto.getCourse());
        entity.setDate(LocalDate.parse(batchDto.getDate())); // Convert String → LocalDate
        entity.setTime(LocalTime.parse(batchDto.getTime())); // Convert String → LocalTime

        BatchEntity savedEntity = batchRepository.save(entity);

        // Convert Entity → DTO
        BatchDto dto = new BatchDto();
        dto.setId(savedEntity.getId());
        dto.setCourse(savedEntity.getCourse());
        dto.setDate(savedEntity.getDate().toString()); // Convert LocalDate → String
        dto.setTime(savedEntity.getTime().toString()); // Convert LocalTime → String

        return dto;
    }

    public List<BatchDto> getAllBatches() {
        return batchRepository.findAll().stream().map(entity -> {
            BatchDto dto = new BatchDto();
            dto.setId(entity.getId());
            dto.setCourse(entity.getCourse());
            dto.setDate(entity.getDate().toString());
            dto.setTime(entity.getTime().toString());
            dto.setActiveFlag(entity.getActiveFlag());
            return dto;
        }).collect(Collectors.toList());
    }

    public BatchDto getBatchById(Long id) {
        BatchEntity entity = batchRepository.findById(id).orElseThrow();
        BatchDto dto = new BatchDto();
        dto.setId(entity.getId());
        dto.setCourse(entity.getCourse());
        dto.setDate(entity.getDate().toString());
        dto.setTime(entity.getTime().toString());
        dto.setActiveFlag(entity.getActiveFlag());
        return dto;
    }

    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }
    public BatchDto updateBatch(Long id, BatchDto batchDto) {
        BatchEntity entity = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + id));

        entity.setCourse(batchDto.getCourse());
        entity.setDate(LocalDate.parse(batchDto.getDate()));
        entity.setTime(LocalTime.parse(batchDto.getTime()));
        entity.setActiveFlag(batchDto.getActiveFlag());

        BatchEntity updatedEntity = batchRepository.save(entity);
        return mapToDto(updatedEntity);
    }
    private BatchDto mapToDto(BatchEntity entity) {
        BatchDto dto = new BatchDto();
        dto.setId(entity.getId());
        dto.setCourse(entity.getCourse());
        dto.setDate(entity.getDate().toString());
        dto.setTime(entity.getTime().toString());
        dto.setActiveFlag(entity.getActiveFlag());
        return dto;
    }
}
