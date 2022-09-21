package com.example.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Template not found exception class
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemplateNotFoundException extends RuntimeException {

    public TemplateNotFoundException(final String message) {
        super(message);
    }
}
