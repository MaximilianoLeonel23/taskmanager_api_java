package com.taskmanager.api.controller;

import com.taskmanager.api.dto.UserDTO;
import com.taskmanager.api.dto.UserSignupRequestDTO;
import com.taskmanager.api.dto.UserSignupResponseDTO;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.UserRepository;
import com.taskmanager.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserSignupResponseDTO> registerUser(@RequestBody UserSignupRequestDTO userSignupRequestDTO) {
        User savedUser = authService.registerUser(userSignupRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserSignupResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User userFound = authService.getUserById(id);
        if (userFound != null) {
            UserDTO response = new UserDTO(userFound.getId(), userFound.getUsername(), userFound.getEmail(), userFound.getCreateAt());
            return ResponseEntity.ok(response);
        }
        return null;
    }
}
