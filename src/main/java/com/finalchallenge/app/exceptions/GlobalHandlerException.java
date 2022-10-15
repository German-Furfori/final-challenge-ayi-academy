package com.finalchallenge.app.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException() {

        Map<String, Object> responseException = new HashMap<>();

        responseException.put(CODE, HttpStatus.BAD_REQUEST.value());
        responseException.put(MESSAGE, NOT_NULL_PROPERTY);

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    // HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableExceptionHandler() {

        Map<String, Object> responseException = new HashMap<>();

        responseException.put(CODE, HttpStatus.BAD_REQUEST.value());
        responseException.put(MESSAGE, JSON_PARSE_ERROR);

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationExceptionHandler() {

        Map<String, Object> responseException = new HashMap<>();

        responseException.put(CODE, HttpStatus.BAD_REQUEST.value());
        responseException.put(MESSAGE, READ_ACCESS_EXCEPTION_INCORRECT_INPUT);

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

}
