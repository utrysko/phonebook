package com.phonebook.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Base Controller. In this controller will be handled а exceptions that may have thrown.
 *
 * @author Vasyl Utrysko.
 * @version 1.0
 */
@Slf4j
public class BaseController {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleExceptions(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return ResponseEntity.ok("Enter correct value for email or phone fields");
    }

    @ExceptionHandler({Exception.class, HibernateException.class})
    public ResponseEntity<String> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.ok("Failed to process request");
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<String> handleIOException(IOException e) {
        log.error(e.getMessage());
        return ResponseEntity.ok("Cannot load photo");
    }
}
