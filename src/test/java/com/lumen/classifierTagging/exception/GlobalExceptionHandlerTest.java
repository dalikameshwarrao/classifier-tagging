package com.lumen.classifierTagging.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

public class GlobalExceptionHandlerTest {

    @Mock
    private Logger mockLogger;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject the mock logger into the exception handler
        globalExceptionHandler = new GlobalExceptionHandler(mockLogger);
    }

    @Test
    void handleRuntimeExceptionShouldLogAndRethrow() {
        // Arrange
        RuntimeException originalException = new RuntimeException("Test exception");

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            globalExceptionHandler.handleRuntimeException(originalException);
        });
        assertEquals("Operation Failed: Test exception", thrown.getMessage());
    }
}
