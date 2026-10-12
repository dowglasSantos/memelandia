package com.app.user_service.dto;

public record ErrorResponseDTO(int status, String error, String message) {
}
