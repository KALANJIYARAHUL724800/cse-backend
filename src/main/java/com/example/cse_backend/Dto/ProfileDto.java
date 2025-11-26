package com.example.cse_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfileDto {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
    @NotNull(message = "phone number is required")
    private Long phone;
    @NotBlank(message = "gender is required")
    private String gender;
    private String bio;
    @NotBlank(message = "dob is required")
    private String dob;
    @NotBlank(message = "address is required")
    private String address;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProfileDto() {
    }

    public ProfileDto(String name, String email, Long phone, String gender, String bio, String dob, String address, String imageUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.bio = bio;
        this.dob = dob;
        this.address = address;
        this.imageUrl = imageUrl;
    }
}
