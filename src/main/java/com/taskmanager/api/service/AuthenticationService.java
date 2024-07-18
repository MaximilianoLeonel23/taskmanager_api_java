package com.taskmanager.api.service;

import com.taskmanager.api.dto.auth.UserSignUpRequestDTO;
import com.taskmanager.api.dto.auth.UserSignUpResponseDTO;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserSignUpResponseDTO signUp(UserSignUpRequestDTO userSignUpRequestDTO) {
        String passwordEncoded = passwordEncoder.encode(userSignUpRequestDTO.password());
        User newUser = new User(
                userSignUpRequestDTO.username(),
                userSignUpRequestDTO.email(),
                passwordEncoded
        );
        User savedUser = userRepository.save(newUser);
        return new UserSignUpResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreateAt()
        );
    }
}
