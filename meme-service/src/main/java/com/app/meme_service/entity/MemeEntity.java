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
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "memeUrl", nullable = false)
    private String memeUrl;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)
    private CategoryEntity category;

    @Column(name = "userID", nullable = false)
    private Long userID;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
