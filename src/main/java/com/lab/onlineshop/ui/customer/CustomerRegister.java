package com.lab.onlineshop.ui.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.ui.FormsEvents;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ViewScoped
public class CustomerRegister extends FormsEvents<Customer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CustomerService customerService;

    @Inject
    private Customer customer;

    private List<Customer> selectedCustomer;

    public Customer getCustomer() {
        return customer;
    }

    @Transactional
    public void saveCustomer(){
        if(saveWithValidation(customer,"Account Created")){
            customer = new Customer();
        }
    }

    public void clearFields() {
        customer.setFirstName(null);
        customer.setLastName(null);
        customer.setPassword(null);
        customer.setEmail(null);
        customer.setAddress(null);
        customer.setAddress(null);
    }

    @Transactional
    public void deleteSelectedCustomer(){
        selectedCustomer.forEach(this::deleteEntity);
        String message = selectedCustomer.size() == 1 ? "Customer removed" : "Customers removed";
        showInformationMessage(message);
    }

    public List<Customer> getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(List<Customer> selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
