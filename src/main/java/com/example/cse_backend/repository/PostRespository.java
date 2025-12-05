    package com.example.cse_backend.repository;

    import com.example.cse_backend.Entity.PostEntity;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface PostRespository extends JpaRepository<PostEntity,Long> {
        @Query("SELECT p FROM PostEntity p ORDER BY p.id DESC")
        List<PostEntity> getAllPostsDesc();
    }
