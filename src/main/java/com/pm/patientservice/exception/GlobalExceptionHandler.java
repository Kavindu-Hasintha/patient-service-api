package com.pm.patientservice.exception;

import com.pm.patientservice.dtos.APIResponseDto;
import com.pm.patientservice.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ResponseCode.INVALID_REQUEST_DATA.setReason(errors.values().toString());
        log.error("Create patient api: " + errors.values().toString() + "...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                APIResponseDto.getInstance(ResponseCode.INVALID_REQUEST_DATA, null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Data integrity violation";
        if (ex.getCause() != null && ex.getCause().getMessage().contains("email")) {
            message = "Email already exists";
        }
        ResponseCode.INVALID_REQUEST_DATA.setReason(message);
        log.error(message + "...");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                APIResponseDto.getInstance(ResponseCode.INVALID_REQUEST_DATA, null));

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = "Invalid input: ID must not be null";
        if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("id")) {
            message = "ID must not be null or invalid";
        }
        ResponseCode.INVALID_REQUEST_DATA.setReason(message);
        log.error(message + "...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                APIResponseDto.getInstance(ResponseCode.INVALID_REQUEST_DATA, null));

    }
}
