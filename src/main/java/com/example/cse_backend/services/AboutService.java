package com.example.cse_backend.services;

import com.example.cse_backend.Entity.AboutEntity;
import com.example.cse_backend.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService {
    @Autowired
    private AboutRepository aboutRepository;

    public List<AboutEntity> allContent()
    {
        return aboutRepository.findAll();
    }
}
