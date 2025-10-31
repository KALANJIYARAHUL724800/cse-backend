package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.AboutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity,Long> {
}
