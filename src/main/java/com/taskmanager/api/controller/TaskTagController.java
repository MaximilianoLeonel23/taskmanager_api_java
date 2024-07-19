package com.taskmanager.api.controller;

import com.taskmanager.api.service.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskTagController {

    @Autowired
    TaskTagService taskTagService;

    @PostMapping("/{taskId}/tags/{tagId}")
    public ResponseEntity<Void> addTagToTask(@PathVariable Long taskId, @PathVariable Long tagId) {
        taskTagService.addTagToTask(taskId, tagId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}/tags/{tagId}")
    public ResponseEntity<Void> removeTagFromTask(@PathVariable Long taskId, @PathVariable Long tagId) {
        taskTagService.removeTagFromTask(taskId, tagId);
        return ResponseEntity.noContent().build();
    }
}
