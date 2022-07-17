package com.lab.onlineshop.ui.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.ui.FormsEvents;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Named
@SessionScoped
public class CustomerRegister extends FormsEvents<Customer> {

    @PersistenceContext
    private EntityManager entityManager;

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    @Transactional
    public void saveCustomer(){
        if(saveWithValidation(customer,"Account Created")){
            customer = new Customer();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
