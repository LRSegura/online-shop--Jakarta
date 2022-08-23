package com.lab.onlineshop.services.dao;

import com.lab.onlineshop.model.AbstractEntity;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.Optional;

public interface Dao {
    EntityManager getEntityManager();
     <T extends AbstractEntity> T getEntity(Class<T> entityClass, Serializable id);
    <T extends AbstractEntity> void save(T entity);
    <T extends AbstractEntity> void save(Iterable<T> entities);

    <T extends AbstractEntity> T saveOrUpdate(T entity);
    <T extends AbstractEntity> void delete(T entity);
    <T extends AbstractEntity> void delete(Iterable<T> entities);
    <T extends AbstractEntity> T merge(T entity);
}
