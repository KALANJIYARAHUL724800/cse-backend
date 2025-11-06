package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.BatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity,Long> {
}
