package com.example.iou_api.service;

import com.example.iou_api.model.User;
import com.example.iou_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {

        // Check user with auth_user_id already exists
        Optional<User> existingUser = userRepository.findByAuthUserId(user.getAuthUserId());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("A user account with the same auth_user_id already exists");
        }

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByAuthId(Integer authUserId) {
        return userRepository.findByAuthUserId(authUserId);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByNameIgnoreCase(name);
    }
}
