package com.lumen.classifierTagging.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lumen.classifierTagging.exception.ClassifierException;
import com.lumen.classifierTagging.service.ClassifierTaggingService;

public class ClassifierTaggingControllerTest {

    @InjectMocks
    private ClassifierTaggingController classifierTaggingController;

    @Mock
    private ClassifierTaggingService classifierTaggingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateClassifierInfoSuccess() {
        // Arrange
        String expectedResponse = "Success: Procedure executed successfully.";
        when(classifierTaggingService.updateClassifierEntities()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = classifierTaggingController.updateClassifierInfo();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testUpdateClassifierInfoBadRequest() {
        // Arrange
        String errorMessage = "Customer number must be a positive non-zero value.";
        doThrow(new IllegalArgumentException(errorMessage)).when(classifierTaggingService).updateClassifierEntities();

        // Act
        ResponseEntity<String> response = classifierTaggingController.updateClassifierInfo();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testUpdateClassifierInfoRuntimeException() {
        // Arrange
        String errorMessage = "Unexpected error occurred.";
        doThrow(new RuntimeException("Some unexpected error")).when(classifierTaggingService).updateClassifierEntities();

        // Act & Assert
        ClassifierException thrown = assertThrows(
                ClassifierException.class,
                () -> classifierTaggingController.updateClassifierInfo()
        );
        assertEquals(errorMessage, thrown.getMessage());
    }
    
    @Test
    void testUpdateClassifierInfoFailed() {
        // Arrange
        String errorMessage = "Error executing store procedure classifier Entities";
        when(classifierTaggingService.updateClassifierEntities()).thenReturn("Failure");

        // Act & Assert
        ClassifierException thrown = assertThrows(
                ClassifierException.class,
                () -> classifierTaggingController.updateClassifierInfo()
        );
        assertNotEquals(errorMessage, thrown.getMessage());
    }

}
