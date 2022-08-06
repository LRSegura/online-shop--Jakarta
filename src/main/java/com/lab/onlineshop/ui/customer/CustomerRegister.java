package com.lab.onlineshop.ui.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ViewScoped
public class CustomerRegister extends RegisterForm<Customer, CustomerService> {

    @Transactional
    public void saveCustomer(){
        if(saveWithValidation(getEntity(),"Account Created")){
            setEntity(new Customer());
        }
    }

    public void clearFields() {
        getEntity().setFirstName(null);
        getEntity().setLastName(null);
        getEntity().setPassword(null);
        getEntity().setEmail(null);
        getEntity().setAddress(null);
        getEntity().setAddress(null);
    }

    @Transactional
    public void deleteSelectedCustomer(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Customer removed" : "Customers removed";
        showInformationMessage(message);
    }

    @Override
    public List<Customer> getEntitiesFromDataBase() {
        return getService().getCustomers();
    }

}
