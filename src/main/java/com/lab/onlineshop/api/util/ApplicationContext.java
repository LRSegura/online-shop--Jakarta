package com.lab.onlineshop.api.util;

import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.model.user.User;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
@Startup
@Local(value = Context.class)
public class ApplicationContext implements Context {
    private User user;
    private Customer customer;

    private final Map<String,Object> loginMap = new HashMap<>();

    public void init(){
        Map<String,Object> map = getRequestMapApplication();
        Object person = map.get("AbstractPerson");
        if(person instanceof User userObject){
            this.customer = null;
            this.user =userObject;
        } else if (person instanceof Customer customerObject) {
            this.user = null;
            this.customer = customerObject;
        }
    }

    @Override
    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<Customer> getLoggedCustomer() {
        return Optional.ofNullable(customer);
    }

    @Override
    public boolean isLoggedUser() {
        return getLoggedUser().isPresent();
    }

    @Override
    public boolean isLoggedCustomer() {
        return getLoggedCustomer().isPresent();
    }

    public Map<String,Object> getRequestMapApplication() {
        return loginMap;
    }

    @Override
    public void logOut(){
        this.user = null;
        this.customer = null;
    }
}
