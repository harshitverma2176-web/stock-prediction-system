package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping 
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        logger.info("Getting all users");
        List<User> users = userRepository.findAll();
        
        ApiResponse<List<User>> response = new ApiResponse<>(
                "Users retrieved successfully", 
                HttpStatus.OK.value(), 
                users
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        logger.info("Getting user by id: " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        ApiResponse<User> response = new ApiResponse<>(
                "User retrieved successfully", 
                HttpStatus.OK.value(), 
                user
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        logger.info("Adding a user");
        User savedUser = userRepository.save(user);
        
        ApiResponse<User> response = new ApiResponse<>(
                "User created successfully", 
                HttpStatus.CREATED.value(), 
                savedUser
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        logger.info("Updating a user");
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        
        User savedUser = userRepository.save(existingUser);
        ApiResponse<User> response = new ApiResponse<>(
                "User updated successfully", 
                HttpStatus.OK.value(), 
                savedUser
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> patchUser(@PathVariable Long id, @RequestBody User partialUser) {
        logger.info("Patching a user");
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        if (partialUser.getUsername() != null) {
            existingUser.setUsername(partialUser.getUsername());
        }
        if (partialUser.getEmail() != null) {
            existingUser.setEmail(partialUser.getEmail());
        }
        if (partialUser.getPassword() != null) {
            existingUser.setPassword(partialUser.getPassword());
        }
        
        User savedUser = userRepository.save(existingUser);
        ApiResponse<User> response = new ApiResponse<>(
                "User patched successfully", 
                HttpStatus.OK.value(), 
                savedUser
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        logger.info("Deleting a user");
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        userRepository.delete(existingUser);
        
        ApiResponse<String> response = new ApiResponse<>(
                "User deleted successfully", 
                HttpStatus.OK.value(), 
                "Deleted ID: " + id
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}