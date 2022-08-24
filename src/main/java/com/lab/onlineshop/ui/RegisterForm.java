package com.lab.onlineshop.ui;

import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.model.Service;
import jakarta.inject.Inject;

import java.util.List;

public abstract class RegisterForm<E extends AbstractEntity, S extends Service> extends EventsForms {

    @Inject
    private S service;

    @Inject
    private E entity;

    private List<E> entitiesSelected;

    public E getFormEntity() {
        return entity;
    }

    public void setFormEntity(E entity) {
        this.entity = entity;
    }

    public void setEntitiesSelected(List<E> entitiesSelected) {
        this.entitiesSelected = entitiesSelected;
    }

    public List<E> getEntitiesSelected() {
        return entitiesSelected;
    }

    public boolean selectedEntities(){
        return entitiesSelected == null || entitiesSelected.isEmpty();
    }

    protected S getFormEntityService() {
        return service;
    }


    public List<E> getEntities(){
        return getEntitiesFromDataBase();
    }

    public abstract List<E> getEntitiesFromDataBase();

    protected void deleteSelectedEntities(String messageToShow){
        if(getEntitiesSelected().isEmpty()){
            showWarningMessage("Select at least one row to delete");
            return;
        }
        getEntitiesSelected().forEach(this::deleteEntity);
        showInformationMessage(messageToShow);
    }
}
