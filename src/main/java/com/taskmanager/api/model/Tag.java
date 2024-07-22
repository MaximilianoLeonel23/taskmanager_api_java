package com.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taskmanager.api.dto.tag.TagRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Tag")
@Table(name = "tags")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<TaskTag> taskTags = new HashSet<>();

    public Tag(TagRequestDTO tagRequestDTO, User user) {
        this.name = tagRequestDTO.name();
        this.user = user;
    }

    public void update(TagRequestDTO tagRequestDTO) {
        if (tagRequestDTO.name() != null && !tagRequestDTO.name().isBlank()) {
            this.name = tagRequestDTO.name();
        }
    }
}
