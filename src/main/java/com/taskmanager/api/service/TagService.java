package com.taskmanager.api.service;

import com.taskmanager.api.dto.tag.TagRequestDTO;
import com.taskmanager.api.dto.tag.TagResponseDTO;
import com.taskmanager.api.model.Tag;
import com.taskmanager.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public TagResponseDTO createTag(TagRequestDTO tagRequestDTO) {
        Tag newTag = new Tag(tagRequestDTO);
        Tag savedTag = tagRepository.save(newTag);
        return new TagResponseDTO(savedTag.getId(), savedTag.getName());
    }

    public List<TagResponseDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagResponseDTO(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }

    public TagResponseDTO getTagById(Long id) {
        return tagRepository.findById(id).map(tag -> new TagResponseDTO(tag.getId(), tag.getName()))
                .orElse(null);
    }


    public TagResponseDTO updateTag(TagRequestDTO tagRequestDTO, Long id) {
        Optional<Tag> tagFound = tagRepository.findById(id);
        if (tagFound.isPresent()) {
            tagFound.get().update(tagRequestDTO);
            Tag updatedTag = tagRepository.save(tagFound.get());
            return new TagResponseDTO(updatedTag.getId(), updatedTag.getName());
        }
        return null;
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
