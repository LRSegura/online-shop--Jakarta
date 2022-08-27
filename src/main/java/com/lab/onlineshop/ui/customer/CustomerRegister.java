package com.lab.onlineshop.ui.customer;

import com.lab.onlineshop.model.Cart;
import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.User;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ViewScoped
public class CustomerRegister extends RegisterForm<Customer> {

    @Inject
    private UserService userService;

    @Inject
    private CustomerService customerService;

    @Transactional
    public void saveCustomer(){
        Customer customer = getFormEntity();
        boolean isUsernameDuplicated = customer.getUserName() != null && (userService.getUser(customer.getUserName()).isPresent() || customerService.getCustomer(customer.getUserName()).isPresent());
        if(isUsernameDuplicated){
            showErrorMessage("UserName is in use");
            return;
        }
        if(saveWithValidation(customer,"Account Created")){
            createCustomerCart(customer);
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
        String message = getEntitiesSelected().size() == 1 ? "Customer removed" : "Customers removed";
        deleteSelectedEntities(message);
    }

    @Override
    public List<Customer> getEntitiesFromDataBase() {
        return customerService.getCustomers();
    }

    private void createCustomerCart(Customer customer){
        Cart cart = new Cart();
        cart.setCustomer(customer);
        saveEntity(cart);
    }
}
