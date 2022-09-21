package com.example.template.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link TemplateNotFoundException}
 */
public class TemplateNotFoundExceptionTest {

    /**
     * Executes {@link TemplateNotFoundException#TemplateNotFoundException(String)} with valid value
     * Expectation: a {@link TemplateNotFoundException} should be created
     */
    @Test
    void whenCreateTemplateNotFoundExceptionShouldReturnTemplateNotFoundExceptionInstance() {
        TemplateNotFoundException result = new TemplateNotFoundException("some message");

        Assertions.assertTrue(result instanceof RuntimeException);
        Assertions.assertEquals("some message", result.getMessage());
    }
}
