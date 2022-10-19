package com.lab.onlineshop.ui;

import com.lab.onlineshop.api.exceptions.EntityFieldException;
import com.lab.onlineshop.api.util.UtilClass;
import com.lab.onlineshop.model.AbstractEntity;

import java.util.Collection;

public interface EntityEvents<T extends AbstractEntity> {
//    void save(T entity);
//    void save(Iterable<T> entities);
//    void delete(T entity);
//    void delete(Iterable<T> entities);
//    void update(T entity);
//    T getEntity(Class<T> entityClass, Long idEntity);
    default void makeValidationBeforeAction(T entity) throws EntityFieldException {
        UtilClass.validateEntityField(entity);
    }
}
