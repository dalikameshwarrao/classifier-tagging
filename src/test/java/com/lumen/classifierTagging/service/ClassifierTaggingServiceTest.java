package com.lumen.classifierTagging.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lumen.classifierTagging.exception.ClassifierException;
import com.lumen.classifierTagging.repository.ClassifierTaggingRepository;

@ExtendWith(MockitoExtension.class)
public class ClassifierTaggingServiceTest {

    @Mock
    private ClassifierTaggingRepository classifierTaggingRepository;

    @InjectMocks
    private ClassifierTaggingService classifierTaggingService;

    @Test
    public void testUpdateClassifierEntitiesSuccess() {
        doNothing().when(classifierTaggingRepository).callUpdateClassifierEntities();

        String result = classifierTaggingService.updateClassifierEntities();

        assertEquals("Success: Procedure executed successfully.", result);
        verify(classifierTaggingRepository, times(1)).callUpdateClassifierEntities();
    }

    @Test
    public void testUpdateClassifierEntitiesThrowsClassifierEntitiesFailedEx() {
        doThrow(new RuntimeException("Database error")).when(classifierTaggingRepository).callUpdateClassifierEntities();

        ClassifierException exception = assertThrows(
            ClassifierException.class,
            () -> classifierTaggingService.updateClassifierEntities()
        );
        
        assertEquals("Error executing store procedure classifier Entities", exception.getMessage());
        verify(classifierTaggingRepository, times(1)).callUpdateClassifierEntities();
    }
}
