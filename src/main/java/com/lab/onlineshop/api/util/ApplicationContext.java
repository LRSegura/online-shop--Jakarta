package com.lab.onlineshop.api.util;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.User;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.faces.context.FacesContext;

import java.util.Map;
import java.util.Optional;

@Singleton
@Startup
@Local(value = Context.class)
public class ApplicationContext implements Context {
    private User user;
    private Customer customer;

    public void init(){
        Map<String,Object> map = getRequestMapApplication();
        Object person = map.get("AbstractPerson");
        if(person instanceof User user){
            this.customer = null;
            this.user =user;
        } else if (person instanceof Customer customer) {
            this.user = null;
            this.customer = customer;
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
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    }

    @Override
    public void logOut(){
        this.user = null;
        this.customer = null;
    }
}
