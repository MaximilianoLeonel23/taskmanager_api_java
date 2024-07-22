package com.taskmanager.api.service;

import com.taskmanager.api.dto.tag.TagResponseDTO;
import com.taskmanager.api.model.Tag;
import com.taskmanager.api.repository.TagRepository;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagResponseDTO> getTagsByUser(Long id) {
        List<Tag> tags = tagRepository.findByUserId(id);
        if (!tags.isEmpty()) {
            return tags.stream().map(tag -> new TagResponseDTO(
                    tag.getId(),
                    tag.getName(),
                    tag.getUser().getId()
            )).collect(Collectors.toList());
        }
        return null;
    }
}
