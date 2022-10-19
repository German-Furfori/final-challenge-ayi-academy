package com.finalchallenge.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.HashMap;
import java.util.Map;

import static com.finalchallenge.app.constants.ExceptionStrings.*;
import static com.finalchallenge.app.constants.HashMapStrings.CODE;
import static com.finalchallenge.app.constants.HashMapStrings.MESSAGE;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runTimeExceptionHandler() {

        Map<String, Object> responseException = new HashMap<>();

        responseException.put(CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseException.put(MESSAGE, GLOBAL_ERROR);

        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<?> webClientRequestExceptionHandler() {

        Map<String, Object> responseException = new HashMap<>();

        responseException.put(CODE, HttpStatus.GATEWAY_TIMEOUT.value());
        responseException.put(MESSAGE, API_NOT_FOUND);

        return new ResponseEntity<>(responseException, HttpStatus.GATEWAY_TIMEOUT);
    }

}
