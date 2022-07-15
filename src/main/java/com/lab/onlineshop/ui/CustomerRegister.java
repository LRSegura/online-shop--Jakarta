package com.lab.onlineshop.ui;

import com.lab.onlineshop.model.Customer;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Named
@SessionScoped
public class CustomerRegister extends FormsEvents {

    @PersistenceContext
    private EntityManager entityManager;

    private final Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    @Transactional
    public void saveCustomer(){
       safeEntity(customer);
       showInformationMessage("Account Created");
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
