package com.taskmanager.api.model;

import com.taskmanager.api.dto.UserSignupRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String email;
    @Column(name = "created_at")
    private LocalDateTime createAt;

    public User(UserSignupRequestDTO userDTO) {
        this.username = userDTO.username();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.createAt = LocalDateTime.now();
    }
}
