package com.lab.onlineshop.ui;

import com.lab.onlineshop.model.AbstractEntity;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

public abstract class FormsEvents implements Serializable {
    protected FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }

    protected void showInformationMessage(String detail){
        showMessage(FacesMessage.SEVERITY_INFO,"Information",detail);
    }

    protected void showWarningMessage(String detail){
        showMessage(FacesMessage.SEVERITY_WARN,"Warning",detail);
    }

    protected void showErrorMessage(String detail){
        showMessage(FacesMessage.SEVERITY_ERROR,"Error",detail);
    }

    protected void showMessage(FacesMessage.Severity severity, String summary, String detail){
        getFacesContext().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    @Transactional
    protected <T extends AbstractEntity> T safeEntity(T entity){
        EntityManager entityManager = getEntityManager();
        if(entity.getId() == null || entityManager.contains(entity)) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        return entity;
    }

    abstract protected EntityManager getEntityManager();

}
