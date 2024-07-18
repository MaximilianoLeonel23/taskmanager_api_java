package com.taskmanager.api.model;

import com.taskmanager.api.dto.tag.TagRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Tag(TagRequestDTO tagRequestDTO) {
        this.name = tagRequestDTO.name();
    }

    public void update(TagRequestDTO tagRequestDTO) {
        if (tagRequestDTO.name() != null && !tagRequestDTO.name().isBlank()) {
            this.name = tagRequestDTO.name();
        }
    }
}
