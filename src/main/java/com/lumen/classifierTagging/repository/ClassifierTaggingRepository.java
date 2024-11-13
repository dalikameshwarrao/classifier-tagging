package com.lumen.classifierTagging.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClassifierTaggingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void callUpdateClassifierEntities() {
        entityManager.createNativeQuery("CALL dh_ingress.sp_update_protection_info()").executeUpdate();
    }
}