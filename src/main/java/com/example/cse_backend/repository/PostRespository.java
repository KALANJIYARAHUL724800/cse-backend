    package com.example.cse_backend.repository;

    import com.example.cse_backend.Entity.PostEntity;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDate;
    import java.util.List;

    @Repository
    public interface PostRespository extends JpaRepository<PostEntity,Long> {
        @Query("SELECT p FROM PostEntity p ORDER BY p.id DESC")
        List<PostEntity> getAllPostsDesc();

        @Query("SELECT COUNT(p) FROM PostEntity p WHERE FUNCTION('DATE', p.updatedAt) = :date")
        Long countPostsByDate(@Param("date") LocalDate date);

    }
