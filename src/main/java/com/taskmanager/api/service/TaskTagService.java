package com.taskmanager.api.service;

import com.taskmanager.api.model.Tag;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.TaskTag;
import com.taskmanager.api.repository.TagRepository;
import com.taskmanager.api.repository.TaskRepository;
import com.taskmanager.api.repository.TaskTagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTagService {

    @Autowired
    TaskTagRepository taskTagRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TagRepository tagRepository;

    @Transactional
    public void addTagToTask(Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        TaskTag taskTag = new TaskTag(task, tag);
        taskTagRepository.save(taskTag);

        task.getTaskTags().add(taskTag);
        tag.getTaskTags().add(taskTag);
    }

    @Transactional
    public void removeTagFromTask(Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        TaskTag taskTag = taskTagRepository.findByTaskAndTag(task, tag).orElseThrow(() -> new RuntimeException("TaskTag not found"));
        taskTagRepository.delete(taskTag);
        task.getTaskTags().remove(taskTag);
        tag.getTaskTags().remove(taskTag);
    }
}
