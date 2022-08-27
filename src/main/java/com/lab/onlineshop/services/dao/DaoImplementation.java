package com.lab.onlineshop.services.dao;

import com.lab.onlineshop.model.AbstractEntity;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
@Local(Dao.class)
public class DaoImplementation implements Dao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public <T extends AbstractEntity> T getEntity(Class<T> entityClass, Serializable id) {
        return entityManager.find(entityClass,id);
    }

    @Override
    public <T extends AbstractEntity> void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public <T extends AbstractEntity> void save(Iterable<T> entities) {
        entities.forEach(this::save);
    }

    @Override
    public <T extends AbstractEntity> T saveOrUpdate(T entity) {
        if(entity.getId() == null || entityManager.contains(entity)) {
            save(entity);
            return entity;
        }
        return merge(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractEntity> void delete(T entity) {
        if(!entityManager.contains(entity)){
            entity = (T) getEntity(entity.getClass(),entity.getId());
        }
        entityManager.remove(entity);
    }

    @Override
    public <T extends AbstractEntity> void delete(Iterable<T> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public <T extends AbstractEntity> T merge(T entity) {
        return entityManager.merge(entity);
    }
}
