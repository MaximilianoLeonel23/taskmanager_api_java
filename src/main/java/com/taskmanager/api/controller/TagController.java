package com.taskmanager.api.controller;

import com.taskmanager.api.dto.tag.TagRequestDTO;
import com.taskmanager.api.dto.tag.TagResponseDTO;
import com.taskmanager.api.repository.TagRepository;
import com.taskmanager.api.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponseDTO> createTag(@RequestBody @Valid TagRequestDTO tagRequestDTO) {
        TagResponseDTO newTag = tagService.createTag(tagRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/tags/{id}").buildAndExpand(newTag.id()).toUri();
        return ResponseEntity.created(uri).body(newTag);
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAllTags() {
        List<TagResponseDTO> allTags = tagService.getAllTags();
        return ResponseEntity.ok().body(allTags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> getTag(@PathVariable Long id) {
        TagResponseDTO tag = tagService.getTagById(id);
        return tag != null ? ResponseEntity.ok(tag) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> updateTag(@RequestBody @Valid TagRequestDTO tagRequestDTO, @PathVariable Long id) {
        TagResponseDTO tag = tagService.updateTag(tagRequestDTO, id);
        return tag != null ? ResponseEntity.ok(tag) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
