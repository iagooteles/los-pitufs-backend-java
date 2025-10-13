package com.los_pitufs.los_pitufs.dto;

import java.time.LocalDate;

import com.los_pitufs.los_pitufs.enums.Role;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String bio;
    private String profilePictureUrl;
    private LocalDate birthDate;
    private String country;
    private Role role;

    public UserDTO() {}

    public UserDTO(Long id, String email, String username, String bio, String profilePictureUrl,
                   LocalDate birthDate, String country, Role role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.birthDate = birthDate;
        this.country = country;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
