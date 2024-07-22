package com.taskmanager.api.util.task;

import com.taskmanager.api.model.Status;
import com.taskmanager.api.model.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                status == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("status"), status);
    }
}
