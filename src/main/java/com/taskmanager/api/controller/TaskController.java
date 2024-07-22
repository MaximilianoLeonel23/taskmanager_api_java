package com.taskmanager.api.controller;

import com.taskmanager.api.dto.task.TaskRequestDTO;
import com.taskmanager.api.dto.task.TaskResponseDTO;
import com.taskmanager.api.dto.task.TaskUpdateRequestDTO;
import com.taskmanager.api.model.Status;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.repository.TaskRepository;
import com.taskmanager.api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<Page<TaskResponseDTO>> getAllTask(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) Status status, @RequestParam(defaultValue = "asc") String sortByDate) {
        Page<TaskResponseDTO> tasks = taskService.getAllTasks(pageNumber, pageSize, status, sortByDate);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filterByTagName")
    public ResponseEntity<Page<TaskResponseDTO>> getTasksByTag(@RequestParam String tagName, Pageable pageable) {
        Page<TaskResponseDTO> taskResponseDTOPage = taskService.getTasksByTagName(tagName, pageable);
        return ResponseEntity.ok(taskResponseDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long id) {
        TaskResponseDTO task = taskService.getTaskById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO task) {
        TaskResponseDTO taskCreated = taskService.createTask(task);
        URI uri = UriComponentsBuilder.fromPath("/tasks/{id}").buildAndExpand(taskCreated.id()).toUri();
        return ResponseEntity.created(uri).body(taskCreated);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody @Valid TaskUpdateRequestDTO task, @PathVariable Long id) {
        TaskResponseDTO taskUpdated = taskService.updateTask(task, id);
        return taskUpdated != null ? ResponseEntity.ok(taskUpdated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
