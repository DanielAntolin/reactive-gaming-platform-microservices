package com.example.apigames.api.controller.impl;

import com.example.apigames.api.commons.dto.ErrorResponse;
import com.example.apigames.api.commons.exceptions.GameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {GameException.class})
    public ResponseEntity<ErrorResponse> handleError(GameException gameException) { // Fixed method name
        var errorResponse = ErrorResponse.builder()
                .codeStatus(gameException.getHttpStatus().value()) // Assuming you need the value, not values()
                .message(gameException.getMessage()) // Fixed to lowercase 'm'
                .build();
        return ResponseEntity.status(gameException.getHttpStatus()).body(errorResponse);
    }
}
