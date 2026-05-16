package com.bancaria.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class, AccountNOtFoundException.class})

    public ResponseEntity<ErrorResponse> handleNOtFound(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
       }

       @ExceptionHandler({EntityNotFoundException.class})

       public ResponseEntity<ErrorResponse> handleEntityNotFound(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
       }

       @ExceptionHandler({InsufficientBalanceException.class, AccountBlockedException.class})

       public ResponseEntity<ErrorResponse> handleBusinessRule(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage()));
       }

       @ExceptionHandler({MethodArgumentNotValidException.class})

       public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
           List<FieldErrorDetail> errors = exception.getBindingResult().getFieldErrors()
           .stream()
           .map(e -> new FieldErrorDetail(e.getField(), e.getDefaultMessage()))
           .toList();

           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro na validação", errors));
       }

    public record ErrorResponse(int status, String message) {
        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    public record FieldErrorDetail(String field, String defaultMessage) {

    }

    public record ValidationErrorResponse(int status, String message, List<FieldErrorDetail> errors) {
    }


}
