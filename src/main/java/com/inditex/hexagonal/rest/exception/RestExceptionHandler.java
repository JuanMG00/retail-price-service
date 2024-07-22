package com.inditex.hexagonal.rest.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
    
    private static final String INVALID_ARGUMENT = "Invalid argument";
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("Resource not found");
        ErrorResponse errorResponse =
                new ErrorResponse("Resource not found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(MethodArgumentNotValidException ex) {

        List<ObjectError> errors = ex.getBindingResult()
                .getAllErrors();

        String errorMsg = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        log.error(errorMsg);
        ErrorResponse errorResponse =
                new ErrorResponse(INVALID_ARGUMENT, errorMsg, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse>
    handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(INVALID_ARGUMENT);
        ErrorResponse errorResponse =
                new ErrorResponse(INVALID_ARGUMENT, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse>
    handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("MissingServletRequestParameterException; " + INVALID_ARGUMENT);
        ErrorResponse errorResponse =
                new ErrorResponse(INVALID_ARGUMENT, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public record ErrorResponse(String error, String message, int status) {
    }

}
