package com.test.entity;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.service.AuditableEntityService;

public abstract class BaseAuditableService<ENTITY extends AuditableEntity> implements AuditableEntityService<ENTITY> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
