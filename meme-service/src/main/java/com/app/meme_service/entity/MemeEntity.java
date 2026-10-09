package com.app.meme_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_meme")
public class MemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_meme")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "URL", nullable = false)
    private String URL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "userEntityId", nullable = false)
    private Long userEntityId;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;
}
