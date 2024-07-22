package com.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.taskmanager.api.dto.auth.UserSignUpRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Encoder;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
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

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Task> tasks;

    public User(String usernameDTO, String emailDTO, String passwordDTO) {
        this.username = usernameDTO;
        this.email = emailDTO;
        this.password = passwordDTO;
        this.createAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
