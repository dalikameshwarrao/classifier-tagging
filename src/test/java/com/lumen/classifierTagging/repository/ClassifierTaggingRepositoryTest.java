package com.lumen.classifierTagging.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@ExtendWith(MockitoExtension.class)
public class ClassifierTaggingRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private ClassifierTaggingRepository classifierTaggingRepository;

    @Test
    public void testCallUpdateClassifierEntities_Success() {
        // Arrange: Mock the behavior of EntityManager and Query
        when(entityManager.createNativeQuery("CALL dh_ingress.sp_update_protection_info()")).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1); // Assume 1 indicates success

        // Act: Call the method under test
        assertDoesNotThrow(() -> classifierTaggingRepository.callUpdateClassifierEntities());

        // Assert: Verify interactions with the mocks
        verify(entityManager, times(1)).createNativeQuery("CALL dh_ingress.sp_update_protection_info()");
        verify(query, times(1)).executeUpdate();
    }

    @Test
    public void testCallUpdateClassifierEntities_Failure() {
        // Arrange: Simulate an exception being thrown by EntityManager or Query
        when(entityManager.createNativeQuery("CALL dh_ingress.sp_update_protection_info()"))
            .thenReturn(query);
        doThrow(new RuntimeException("Database error")).when(query).executeUpdate();

        // Act & Assert: Verify that an exception is propagated correctly
        Exception exception = assertThrows(
            RuntimeException.class,
            () -> classifierTaggingRepository.callUpdateClassifierEntities()
        );

        assertEquals("Database error", exception.getMessage());
        verify(entityManager, times(1)).createNativeQuery("CALL dh_ingress.sp_update_protection_info()");
        verify(query, times(1)).executeUpdate();
    }
}
