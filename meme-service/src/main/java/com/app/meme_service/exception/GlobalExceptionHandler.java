package com.app.meme_service.exception;

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
    private ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    private ResponseEntity<String>  categoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MemeNotFoudException.class)
    private ResponseEntity<String> memeNotFoundException(MemeNotFoudException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
