package com.sainath.ipldashboard.exception;

import com.sainath.ipldashboard.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleTeamNotFoundException(TeamNotFoundException e) {
        log.error("Failed to find a team", e);
        ApiResponse<Void> errorResponse = new ApiResponse<>();
        errorResponse.setErrorMessage(e.getMessage());
        errorResponse.setStatus("error");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
