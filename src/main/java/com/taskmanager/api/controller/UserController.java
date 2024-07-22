package com.taskmanager.api.controller;

import com.taskmanager.api.dto.tag.TagResponseDTO;
import com.taskmanager.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}/tags")
    public ResponseEntity<List<TagResponseDTO>> getTagsByUser(@PathVariable Long id) {
        List<TagResponseDTO> tags = userService.getTagsByUser(id);
        if (tags.isEmpty()) {
            throw new RuntimeException("Not tags found for that user");
        }
        return ResponseEntity.ok(tags);
    }
}
