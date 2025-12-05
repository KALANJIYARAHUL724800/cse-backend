package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.TestiMonialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestiMonialsRepository extends JpaRepository<TestiMonialsEntity,Long> {
    @Query("SELECT t FROM TestiMonialsEntity t WHERE t.enrollno = :enrollno")
    Optional<TestiMonialsEntity> findByEnrollNo(@Param("enrollno") Long enrollno);
}
