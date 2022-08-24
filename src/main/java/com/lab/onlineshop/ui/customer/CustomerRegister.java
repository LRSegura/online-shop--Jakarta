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
        if(saveWithValidation(getFormEntity(),"Account Created")){
            setFormEntity(new Customer());
        }
    }

    public void clearFields() {
        getFormEntity().setFirstName(null);
        getFormEntity().setLastName(null);
        getFormEntity().setPassword(null);
        getFormEntity().setEmail(null);
        getFormEntity().setAddress(null);
        getFormEntity().setAddress(null);
    }

    @Transactional
    public void deleteSelectedCustomer(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Customer removed" : "Customers removed";
        showInformationMessage(message);
    }

    @Override
    public List<Customer> getEntitiesFromDataBase() {
        return getFormEntityService().getCustomers();
    }

}
