package com.taskmanager.api.controller;

import com.taskmanager.api.dto.auth.UserSignInRequestDTO;
import com.taskmanager.api.dto.auth.UserSignInResponseDTO;
import com.taskmanager.api.dto.auth.UserSignUpRequestDTO;
import com.taskmanager.api.dto.auth.UserSignUpResponseDTO;
import com.taskmanager.api.model.User;
import com.taskmanager.api.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> signUp(@RequestBody @Valid UserSignUpRequestDTO userSignUpRequestDTO) {
        UserSignUpResponseDTO user = authService.signUp(userSignUpRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/auth/signup/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserSignInResponseDTO> login(@RequestBody @Valid UserSignInRequestDTO userSignInRequestDTO) {
        UserSignInResponseDTO user = authService.signIn(userSignInRequestDTO);
        return ResponseEntity.ok(user);
    }
}
