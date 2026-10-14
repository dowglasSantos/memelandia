package br.com.ebac.memelandia.exception;

import br.com.ebac.memelandia.dto.ErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.FeignClientException.class)
    private ResponseEntity<ErrorResponse> handleFeignException(FeignException exception) {
        HttpStatus status;

        if (exception.status() == 400) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception.status() == 404) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception.status() == 409) {
            status = HttpStatus.CONFLICT;
        } else if (exception.status() == 503) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        } else {
            status = HttpStatus.BAD_GATEWAY;
        }

        ErrorResponse error = new ErrorResponse(
                status.value(),
                "Communication error with a microservice"
        );

        return ResponseEntity.status(status).body(error);
    }
}
