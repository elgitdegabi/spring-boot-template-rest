package com.example.template.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link TemplateNotFoundException}
 */
public class TemplateNotFoundExceptionTest {

    /**
     *
     */
    @Test
    void whenCreateTemplateNotFoundExceptionShouldReturnTemplateNotFoundExceptionInstance() {
        TemplateNotFoundException result = new TemplateNotFoundException("some message");

        Assertions.assertTrue(result instanceof RuntimeException);
        Assertions.assertEquals("some message", result.getMessage());
    }
}
