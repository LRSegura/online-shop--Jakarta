package com.lab.onlineshop;

import com.lab.onlineshop.entities.Customer;
import jakarta.annotation.PostConstruct;
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
public class CustomerRegister implements Serializable {

//    @PersistenceContext
//    private EntityManager entityManager;

    private final Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    @Transactional
    public void saveCustomer(){
//        entityManager.persist(customer);
        info();
    }

    private void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Account Created"));
    }

    public void saveCustomer2(){
        info();
    }
}
