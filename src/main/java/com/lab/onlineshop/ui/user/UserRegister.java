package com.lab.onlineshop.ui.user;

import com.lab.onlineshop.model.User;
import com.lab.onlineshop.model.UserLevel;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class UserRegister extends RegisterForm<User, UserService> {

    @Transactional
    public void saveUser(){
        if(saveWithValidation(getEntity(),"User Created")){
            setEntity(new User());
        }
    }

    @Transactional
    public void deleteSelectedUsers(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "User removed" : "Users removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        getEntity().setUserLevel(null);
        getEntity().setIsActive(false);
        getEntity().setEmail(null);
        getEntity().setRegisterDate(null);
        getEntity().setFirstName(null);
        getEntity().setLastName(null);
        getEntity().setPassword(null);
    }

    @Override
    public List<User> getEntitiesFromDataBase() {
        return getService().getUsers();
    }

    public List<UserLevel> getListUserLevel(){
        return Arrays.stream(UserLevel.values()).toList();
    }
}
