package com.example.cse_backend.repository;

import com.example.cse_backend.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    @Query("SELECT c FROM CourseEntity c WHERE c.courseName LIKE %:value%")
    List<CourseEntity> searchByName(@Param("value") String value);
    @Query("SELECT c FROM CourseEntity c WHERE c.id = (SELECT MAX(c2.id) FROM CourseEntity c2)")
    CourseEntity findLatestCourse();
}
