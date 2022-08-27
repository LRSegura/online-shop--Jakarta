package com.lab.onlineshop.ui.user;

import com.lab.onlineshop.model.Cart;
import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;
import com.lab.onlineshop.model.UserLevel;
import com.lab.onlineshop.services.cart.CartService;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class UserRegister extends RegisterForm<User> {


    @Inject
    private UserService userService;

    @Inject
    private CustomerService customerService;

    @Transactional
    public void saveUser(){
        User user = getFormEntity();
        boolean isUsernameDuplicated = user.getUserName() != null && (userService.getUser(user.getUserName()).isPresent() || customerService.getCustomer(user.getUserName()).isPresent());
        if(isUsernameDuplicated){
            showErrorMessage("UserName is in use");
            return;
        }
        if(saveWithValidation(user,"User Created")){
            setFormEntity(new User());
        }
    }

    @Transactional
    public void deleteSelectedUsers(){
        String message = getEntitiesSelected().size() == 1 ? "User removed" : "Users removed";
        deleteSelectedEntities(message);
    }

    public void clearFields(){
        getFormEntity().setUserLevel(null);
        getFormEntity().setIsActive(false);
        getFormEntity().setEmail(null);
        getFormEntity().setRegisterDate(null);
        getFormEntity().setFirstName(null);
        getFormEntity().setLastName(null);
        getFormEntity().setPassword(null);
    }

    @Override
    public List<User> getEntitiesFromDataBase() {
        return userService.getUsers();
    }

    public List<UserLevel> getListUserLevel(){
        return Arrays.stream(UserLevel.values()).toList();
    }
}
