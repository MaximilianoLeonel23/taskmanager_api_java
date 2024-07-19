package com.taskmanager.api.service;

import com.taskmanager.api.dto.tag.TagRequestDTO;
import com.taskmanager.api.dto.tag.TagResponseDTO;
import com.taskmanager.api.model.Tag;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.TagRepository;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserRepository userRepository;

    public TagResponseDTO createTag(TagRequestDTO tagRequestDTO) {
        Optional<User> userFound = userRepository.findById(tagRequestDTO.userId());
        if (userFound.isPresent()) {
            User user = userFound.get();
            Tag newTag = new Tag(tagRequestDTO, user);
            Tag savedTag = tagRepository.save(newTag);
            return new TagResponseDTO(savedTag.getId(), savedTag.getName(), savedTag.getUser().getId());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<TagResponseDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagResponseDTO(tag.getId(), tag.getName(), tag.getUser().getId()))
                .collect(Collectors.toList());
    }

    public TagResponseDTO getTagById(Long id) {
        return tagRepository.findById(id).map(tag -> new TagResponseDTO(tag.getId(), tag.getName(), tag.getUser().getId()))
                .orElse(null);
    }


    public TagResponseDTO updateTag(TagRequestDTO tagRequestDTO, Long id) {
        Optional<Tag> tagFound = tagRepository.findById(id);
        if (tagFound.isPresent()) {
            tagFound.get().update(tagRequestDTO);
            Tag updatedTag = tagRepository.save(tagFound.get());
            return new TagResponseDTO(updatedTag.getId(), updatedTag.getName(), updatedTag.getUser().getId());
        }
        return null;
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
