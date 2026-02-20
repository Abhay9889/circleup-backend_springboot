package com.CircleUp.CircleUp.service;

import com.CircleUp.CircleUp.model.User;
import com.CircleUp.CircleUp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public List<User>searchByName(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateProfile(Long id,User updateUser){
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: "+id));
        user.setName(updateUser.getName());
        user.setBio(updateUser.getBio());
        user.setLocation(updateUser.getLocation());
        user.setProfilePictureUrl(updateUser.getProfilePictureUrl());
        return userRepository.save(user);
    }
    public User getUserById(Long id) { return userRepository.findById(id) .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
