package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {

}
