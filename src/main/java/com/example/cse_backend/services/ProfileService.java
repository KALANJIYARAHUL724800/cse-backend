package com.example.cse_backend.services;

import com.example.cse_backend.Dto.ProfileDto;
import com.example.cse_backend.Entity.ProfileEntity;
import com.example.cse_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity insert(ProfileDto data)
    {
        ProfileEntity obj = new ProfileEntity();
        obj.setName(data.getName());
        obj.setAddress(data.getAddress());
        obj.setBio(data.getBio());
        obj.setDob(data.getDob());
        obj.setEmail(data.getEmail());
        obj.setGender(data.getGender());
        obj.setPhone(data.getPhone());
        obj.setImageUrl(data.getImageUrl());
        return profileRepository.save(obj);
    }

    public List<ProfileEntity> showAll()
    {
        return profileRepository.findAll();
    }
}
