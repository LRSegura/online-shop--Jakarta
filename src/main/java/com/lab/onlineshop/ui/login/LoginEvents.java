package com.lab.onlineshop.ui.login;

import com.lab.onlineshop.api.util.Context;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.EventsForms;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "login")
@ViewScoped
public class LoginEvents extends EventsForms {

    @EJB
    private Context applicationContext;
    private String userName;
    private String password;

    @Inject
    private UserService userService;

    @Inject
    private CustomerService customerService;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
        boolean successUser = userService.getUser(userName,password).map(user -> {
            applicationContext.getRequestMapApplication().put("AbstractPerson",user);
            return user;
        }).isPresent();

        boolean successCustomer = customerService.getCustomer(userName,password).map(customer -> {
            applicationContext.getRequestMapApplication().put("AbstractPerson",customer);
            return customer;
        }) .isPresent();
        if(successCustomer || successUser){
            applicationContext.init();
            return "Home";
        }
        showErrorMessage("Username or password incorrect");
        return null;
    }
}
