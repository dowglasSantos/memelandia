package br.com.ebac.memelandia.dto;

import java.time.LocalDateTime;

public class MemeDTO {
    private Long id;
    private String name;
    private String description;
    private String memeUrl;
    private Long categoryID;
    private Long userID;
    private LocalDateTime createdAt;

    public MemeDTO() {}

    public MemeDTO(String name, String description, String memeUrl) {
        this.name = name;
        this.description = description;
        this.memeUrl = memeUrl;
    }

    public MemeDTO(Long id, String name, String description, String memeUrl, Long categoryID, Long userID, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.memeUrl = memeUrl;
        this.categoryID = categoryID;
        this.userID = userID;
        this.createdAt = createdAt;
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

    public String getMemeUrl() {
        return memeUrl;
    }

    public void setMemeUrl(String memeUrl) {
        this.memeUrl = memeUrl;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userEntityId) {
        this.userID = userEntityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
