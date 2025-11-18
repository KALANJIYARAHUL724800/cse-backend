package com.example.cse_backend.services;

import com.example.cse_backend.Dto.PostDto;
import com.example.cse_backend.Entity.CommentsEntity;
import com.example.cse_backend.Entity.PostEntity;
import com.example.cse_backend.repository.CommentsRepository;
import com.example.cse_backend.repository.PostRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRespository postRespository;
    @Autowired
    private CommentsRepository commentsRepository;


    public PostEntity insert(PostDto data) {
        PostEntity obj = new PostEntity();
        obj.setTitle(data.getTitle());
        obj.setImageUrl(data.getImageUrl());
        PostEntity savedPost = postRespository.save(obj);
        CommentsEntity dummy = new CommentsEntity();
        dummy.setLikes(0L);
        dummy.setComments("No comments yet");
        dummy.setPost(savedPost);
        commentsRepository.save(dummy);
        return savedPost;
    }

    public List<PostEntity> findAll()
    {
        return postRespository.findAll();
    }
    public PostEntity findOne(Long id) {
        return postRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
    public PostEntity update(Long id,PostDto data)
    {
        PostEntity obj =  postRespository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        obj.setTitle(data.getTitle());
        obj.setImageUrl(data.getImageUrl());
        return postRespository.save(obj);
    }
    public void deletePost(Long id)
    {
        postRespository.deleteById(id);
    }
}
