package com.taskmanager.api.controller;

import com.taskmanager.api.dto.UserDTO;
import com.taskmanager.api.dto.UserSignupRequestDTO;
import com.taskmanager.api.dto.UserSignupResponseDTO;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserSignupResponseDTO> registerUser(@RequestBody UserSignupRequestDTO userSignupRequestDTO) {
        User user = new User(userSignupRequestDTO);
        User savedUser = userRepository.save(user);

        URI uri = UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserSignupResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            UserDTO response = new UserDTO(userFound.get().getId(), userFound.get().getUsername(), userFound.get().getEmail(), userFound.get().getCreateAt());
            return ResponseEntity.ok(response);
        }
        return null;
    }

}
