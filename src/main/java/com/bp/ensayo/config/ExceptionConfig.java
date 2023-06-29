package com.bp.ensayo.config;

import com.bp.ensayo.exception.AccountException;
import com.bp.ensayo.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

    @ExceptionHandler({AccountException.class, CustomerException.class})
    public ResponseEntity<Object> accountException(RuntimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
    }
}
