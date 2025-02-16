package com.example.iou_api.controller;

import com.example.iou_api.dto.UsernameDTO;
import com.example.iou_api.model.Currency;
import com.example.iou_api.model.User;
import com.example.iou_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/iou/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Fetch user by auth_user_id
    @GetMapping("/auth/{authUserId}")
    public ResponseEntity<User> getUserByAuthUserId(@PathVariable Integer authUserId) {
        Optional<User> user = userService.getUserByAuthId(authUserId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch user by user_id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestBody UsernameDTO request) {
        boolean exists = userService.existsByName(request.getName());

        return ResponseEntity.ok(exists);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
