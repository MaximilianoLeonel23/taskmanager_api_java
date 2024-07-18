package com.taskmanager.api.service;

import com.taskmanager.api.dto.task.TaskRequestDTO;
import com.taskmanager.api.dto.task.TaskResponseDTO;
import com.taskmanager.api.dto.task.TaskUpdateRequestDTO;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(t -> new TaskResponseDTO(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getStatus(),
                        t.getCreatedAt(),
                        t.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        return taskRepository.findById(id).map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt())).orElse(null);
    }

    public TaskResponseDTO createTask(TaskRequestDTO task) {
        Task newTask = new Task(task);
        Task createdTask = taskRepository.save(newTask);
        return new TaskResponseDTO(createdTask.getId(),
                createdTask.getTitle(),
                createdTask.getDescription(),
                createdTask.getStatus(),
                createdTask.getCreatedAt(),
                createdTask.getUpdatedAt());
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
                    updatedTask.getUpdatedAt()
            );
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
