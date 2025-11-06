package com.example.cse_backend.controller;

import com.example.cse_backend.Dto.BatchDto;
import com.example.cse_backend.services.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/insert")
    public ResponseEntity<?> saveBatch(@Valid @RequestBody BatchDto batchDto, BindingResult result) {
        if (result.hasErrors()) {
            // Create a map of field name -> error message
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors); // now type is fine
        }

        BatchDto savedBatch = batchService.saveBatch(batchDto);
        return ResponseEntity.ok(savedBatch);
    }


    @GetMapping
    public ResponseEntity<List<BatchDto>> getAllBatches() {
        List<BatchDto> batches = batchService.getAllBatches();
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchDto> getBatchById(@PathVariable Long id) {
        BatchDto batch = batchService.getBatchById(id);
        return ResponseEntity.ok(batch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBatch(@PathVariable Long id, @Valid @RequestBody BatchDto batchDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        BatchDto updatedBatch = batchService.updateBatch(id, batchDto);
        return ResponseEntity.ok(updatedBatch);
    }
}
