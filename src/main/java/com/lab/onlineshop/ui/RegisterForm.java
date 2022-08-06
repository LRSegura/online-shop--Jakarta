package com.lab.onlineshop.ui;

import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.model.Service;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public abstract class RegisterForm<E extends AbstractEntity, S extends Service> extends EventsForms<E> {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private S service;

    @Inject
    private E entity;

    private List<E> entitiesSelected;

    public E getEntity() {
        return entity;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void setEntitiesSelected(List<E> entitiesSelected) {
        this.entitiesSelected = entitiesSelected;
    }

    public List<E> getEntitiesSelected() {
        return entitiesSelected;
    }

    protected S getService() {
        return service;
    }


    public List<E> getEntities(){
        return getEntitiesFromDataBase();
    }

    public abstract List<E> getEntitiesFromDataBase();
}
