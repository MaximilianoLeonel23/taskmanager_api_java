package com.taskmanager.api.service;

import com.taskmanager.api.dto.task.TaskRequestDTO;
import com.taskmanager.api.dto.task.TaskResponseDTO;
import com.taskmanager.api.dto.task.TaskUpdateRequestDTO;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.TaskRepository;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<TaskResponseDTO> getAllTasks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser() != null ? task.getUser().getId() : null
        ));
    }
    public TaskResponseDTO getTaskById(Long id) {
        return taskRepository.findById(id).map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser().getId())).orElse(null);
    }

    public TaskResponseDTO createTask(TaskRequestDTO task) {
        Optional<User> userFound = userRepository.findById(task.userId());
        if (userFound.isPresent()) {
            User user = userFound.get();
            Task newTask = new Task(task, user);
            Task createdTask = taskRepository.save(newTask);
            return new TaskResponseDTO(createdTask.getId(),
                    createdTask.getTitle(),
                    createdTask.getDescription(),
                    createdTask.getStatus(),
                    createdTask.getCreatedAt(),
                    createdTask.getUpdatedAt(),
                    createdTask.getUser().getId());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public TaskResponseDTO updateTask(TaskUpdateRequestDTO task, Long id) {
        Optional<Task> taskFound = taskRepository.findById(id);
        if (taskFound.isPresent()) {
            taskFound.get().update(task);
            Task updatedTask = taskRepository.save(taskFound.get());
            return new TaskResponseDTO(
                    updatedTask.getId(),
                    updatedTask.getTitle(),
                    updatedTask.getDescription(),
                    updatedTask.getStatus(),
                    updatedTask.getCreatedAt(),
                    updatedTask.getUpdatedAt(),
                    updatedTask.getUser().getId()
            );
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
