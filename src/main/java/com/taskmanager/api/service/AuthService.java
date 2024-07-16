package com.taskmanager.api.service;

import com.taskmanager.api.dto.UserDTO;
import com.taskmanager.api.dto.UserSignupRequestDTO;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserSignupRequestDTO userSignupRequestDTO) {
        String encodedPassword = passwordEncoder.encode(userSignupRequestDTO.password());
        User user = new User(userSignupRequestDTO.username(), userSignupRequestDTO.email(), encodedPassword);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> userFound = userRepository.findById(id);
        return userFound.orElse(null);
    }
}
