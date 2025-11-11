package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.TestiMonialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestiMonialsRepository extends JpaRepository<TestiMonialsEntity,Long> {
}
