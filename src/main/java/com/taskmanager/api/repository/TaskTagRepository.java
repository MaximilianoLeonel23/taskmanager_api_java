package com.taskmanager.api.repository;

import com.taskmanager.api.model.Tag;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {

    Optional<TaskTag> findByTaskAndTag(Task task, Tag tag);
}
