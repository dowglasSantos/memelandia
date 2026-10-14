package br.com.ebac.memelandia.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private List<MemeDTO> memes;

    public CategoryDTO() {
        memes = new ArrayList<>();
    }

    public CategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Long id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public void addMeme(MemeDTO meme) {
        memes.add(meme);
        meme.setCategoryID(this.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
