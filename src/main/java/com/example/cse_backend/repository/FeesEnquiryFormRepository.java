package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.FeesEnquiryFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeesEnquiryFormRepository extends JpaRepository<FeesEnquiryFormEntity,Long> {
    List<FeesEnquiryFormEntity> findByCurrentDateBetween(LocalDate startDate, LocalDate endDate);

}
