package com.app.meme_service.exception;

import com.app.meme_service.dto.ErrorResponseDTO;
import com.app.meme_service.exception.category_exception.CategoryAlreadyExistsException;
import com.app.meme_service.exception.category_exception.CategoryNotFoundException;
import com.app.meme_service.exception.meme_exception.MemeNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<ErrorResponseDTO> categoryNotFoundException(CategoryNotFoundException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "CATEGORY NOT FOUND",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    private ResponseEntity<ErrorResponseDTO>  categoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "CATEGORY ALREADY IN USE",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(MemeNotFoudException.class)
    private ResponseEntity<ErrorResponseDTO> memeNotFoundException(MemeNotFoudException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "MEME NOT FOUND",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
