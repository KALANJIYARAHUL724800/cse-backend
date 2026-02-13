package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateEntity,Long> {
}
