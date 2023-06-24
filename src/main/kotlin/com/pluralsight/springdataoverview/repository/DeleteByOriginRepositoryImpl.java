package com.pluralsight.springdataoverview.repository;

import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

public class DeleteByOriginRepositoryImpl implements DeleteByOriginRepository{

    private final EntityManager entityManager;

    public DeleteByOriginRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteByOrigin(@NotNull String origin) {
    entityManager.createNativeQuery("DELETE FROM flight WHERE origin = ?;").setParameter(1,origin).executeUpdate();
    }
}
