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
        if(saveWithValidation(getFormEntity(),"User Created")){
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
        return getFormEntityService().getUsers();
    }

    public List<UserLevel> getListUserLevel(){
        return Arrays.stream(UserLevel.values()).toList();
    }
}
