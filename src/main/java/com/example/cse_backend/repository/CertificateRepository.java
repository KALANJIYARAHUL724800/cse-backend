package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateEntity,Long> {

    @Query("SELECT c FROM CertificateEntity c WHERE c.enrollNumber = :enrollNo")
    List<CertificateEntity> findByEnrollNo(@Param("enrollNo") Integer enrollNo);

}
