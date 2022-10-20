package com.lab.onlineshop.ui;

import com.lab.onlineshop.api.exceptions.EntityFieldException;
import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.transaction.Transactional;
public abstract class AbstractEntityEvents<T extends AbstractEntity> implements EntityEvents<T> {

    @EJB
    private Dao dao;

    @Transactional
    public void save(T entity) {
        dao.save(entity);
    }
    @Transactional
    public void save(Iterable<T> entities) {
        dao.save(entities);
    }

    @Transactional
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Transactional
    public void delete(Iterable<T> entities) {
        dao.delete(entities);
    }

    @Transactional
    public void update(T entity) {
        dao.saveOrUpdate(entity);
    }

    @Transactional
    public T getEntity(Class<T> entityClass, Long idEntity) {
        return dao.getEntity(entityClass,idEntity);
    }

    @Transactional
    public void saveWithValidation(T entity) throws EntityFieldException {
        makeValidationBeforeAction(entity);
        dao.save(entity);
    }

    public void saveWithValidation(Iterable<T> entities) throws EntityFieldException{
        for (T entity: entities) {
            makeValidationBeforeAction(entity);
            dao.saveOrUpdate(entity);
        }
    }

    @Transactional
    public void updateWithValidation(T entity) throws EntityFieldException {
        makeValidationBeforeAction(entity);
        dao.saveOrUpdate(entity);
    }

    public Dao getDao() {
        return dao;
    }
}
