package com.lab.onlineshop.ui;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import com.lab.onlineshop.model.AbstractEntity;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.lab.onlineshop.api.util.UtilClass.getFieldsFromEntity;

public abstract class EventsForms<T extends AbstractEntity> implements Serializable {

    public String goToHome(){
        return "Home";
    }

    protected FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }

    protected void showInformationMessage(String detail){
        showMessage(FacesMessage.SEVERITY_INFO,"Information: ",detail);
    }

    protected void showWarningMessage(String detail){
        showMessage(FacesMessage.SEVERITY_WARN,"Warning: ",detail);
    }

    protected void showErrorMessage(String detail){
        showMessage(FacesMessage.SEVERITY_ERROR,"Error: ",detail);
    }

    protected void showMessage(FacesMessage.Severity severity, String summary, String detail){
        getFacesContext().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    protected boolean saveWithValidation(T entity, final String message){
        if(isEntityFieldsEmpty(entity)){
            return false;
        }
        safeEntity(entity);
        showInformationMessage(message);
        return true;
    }

    @Transactional
    protected T safeEntity(T entity){
        EntityManager entityManager = getEntityManager();
        if(entity.getId() == null || entityManager.contains(entity)) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        return entity;
    }

    @Transactional
    protected void deleteEntity(AbstractEntity entity){
        EntityManager entityManager = getEntityManager();
        Class<? extends AbstractEntity> entityClass = entity.getClass();
        if(!entityManager.contains(entity)){
            entity = entityManager.find(entityClass, entity.getId());
        }
        entityManager.remove(entity);
    }

    abstract protected EntityManager getEntityManager();

    public boolean isEntityFieldsEmpty(T entity) {
        List<String> errors = new ArrayList<>();
        for (Field field : getFieldsFromEntity(entity)){
             Column column = field.getAnnotation(Column.class);
             if(column != null && !column.name().equalsIgnoreCase("id") && !column.nullable()){
                 field.setAccessible(true);
                 try {
                     Object value = field.get(entity);
                     if(value == null || (value instanceof String stringValue && stringValue.isEmpty())) {
                         Description description = field.getAnnotation(Description.class);
                         String messageError;
                         if(description == null){
                              messageError= String.format("The field '%s' from the entity '%s' should have the Description Annotation", field.getName(), entity.getClass().getName());
                             throw new RuntimeException(messageError);
                         }
                         InjectedDate injectedDate = field.getAnnotation(InjectedDate.class);
                         if(injectedDate == null){
                             messageError = String.format("The field '%s' is empty", description.value());
                             errors.add(messageError);
                         }
                     }
                 } catch (IllegalAccessException e) {
                     throw new RuntimeException("Field validation Error ");
                 }
             }
        }
        errors.forEach(this::showErrorMessage);
        return !errors.isEmpty();
    }
}