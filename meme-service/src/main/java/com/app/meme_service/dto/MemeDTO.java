package com.app.meme_service.dto;

import java.io.Serializable;

public record MemeDTO(String name, String description, String URL, Long userID) implements Serializable {
}
