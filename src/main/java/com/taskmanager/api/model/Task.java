package com.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taskmanager.api.dto.task.TaskRequestDTO;
import com.taskmanager.api.dto.task.TaskUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Task")
@Table(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskTag> taskTags = new HashSet<>();

    public Task(TaskRequestDTO t, User user) {
        this.title = t.title();
        this.description = t.description();
        this.status = Status.valueOf("PENDING");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.user = user;
    }

    public void update(TaskUpdateRequestDTO task) {
        if (task.title() != null && !task.title().isBlank()) {
            this.title = task.title();
        }
        if (task.description() != null && !task.description().isBlank()) {
            this.description = task.description();
        }
        if (task.status() != null && !task.status().name().isEmpty()){
            this.status = task.status();
        }
        this.updatedAt = LocalDateTime.now();
    }
}
