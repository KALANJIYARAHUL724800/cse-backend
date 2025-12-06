package com.example.cse_backend.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserDto {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;
    @NotBlank(message = "Confirm Password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String confirmPassword;
    private boolean userType;
    private String profile;
    private Long mobile;
    private String bio;
    private Date dob;
    private String address;
    private String gender;
    @NotNull(message = "Enroll number is required")
    private Integer enrollNo;

    public UserDto(String name, String email, String password, String confirmPassword, boolean userType, String profile, Long mobile, String bio, Date dob) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userType = userType;
        this.profile = profile;
        this.mobile = mobile;
        this.bio = bio;
        this.dob = dob;
    }
    public UserDto(String name, Integer enrollNo, String email, Long mobile,
                   String gender, String address) {
        this.name = name;
        this.enrollNo = enrollNo;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public Integer getEnrollNo() {
        return enrollNo;
    }

    public void setEnrollNo(Integer enrollNo) {
        this.enrollNo = enrollNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserDto(String name, String email, String password, String confirmPassword, boolean userType, String profile, Long mobile, String bio, Date dob, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userType = userType;
        this.profile = profile;
        this.mobile = mobile;
        this.bio = bio;
        this.dob = dob;
        this.address = address;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDto(String name, String email, String password, String confirmPassword, boolean userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userType = userType;
    }

    public UserDto() {
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
