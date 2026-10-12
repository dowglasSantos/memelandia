package com.app.meme_service.dto;

public record ErrorResponseDTO(int status, String error, String message) {
}
