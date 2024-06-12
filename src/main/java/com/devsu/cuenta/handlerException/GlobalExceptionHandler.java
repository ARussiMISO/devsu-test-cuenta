package com.devsu.cuenta.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsu.cuenta.handlerException.dto.ExceptionMessage;
import com.devsu.cuenta.handlerException.exception.AlreadyExistsException;
import com.devsu.cuenta.handlerException.exception.NotFoundException;

import jakarta.validation.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException exception){
        ExceptionMessage exceptionGenerated = new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(exceptionGenerated.getStatus()).body(exceptionGenerated);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> handleAlreadyExistsException(AlreadyExistsException exception){
        ExceptionMessage exceptionGenerated = new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(exceptionGenerated.getStatus()).body(exceptionGenerated);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionMessage> handleValidationException(ValidationException exception){
        ExceptionMessage exceptionGenerated = new ExceptionMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(exceptionGenerated.getStatus()).body(exceptionGenerated);
    }


}
