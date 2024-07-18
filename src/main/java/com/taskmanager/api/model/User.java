package com.taskmanager.api.model;

import com.taskmanager.api.dto.auth.UserSignUpRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.Encoder;
import java.time.LocalDateTime;


@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    @Email
    private String email;
    @Column(name = "created_at")
    private LocalDateTime createAt;

    public User(String usernameDTO, String emailDTO, String passwordDTO) {
        this.username = usernameDTO;
        this.email = emailDTO;
        this.password = passwordDTO;
        this.createAt = LocalDateTime.now();
    }
}
