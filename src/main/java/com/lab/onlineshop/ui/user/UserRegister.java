package com.lab.onlineshop.ui.user;

import com.lab.onlineshop.model.User;
import com.lab.onlineshop.model.UserLevel;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.FormsEvents;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class UserRegister extends FormsEvents<User> {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserService userService;

    @Inject
    private User user;

    private List<User> usersSelected;

    @PostConstruct
    public void init(){
    }

    private boolean isRowSelected;

    private int countRowSelected;
    public boolean isRowSelected() {
        return isRowSelected;
    }

    public User getUser(){
        return user;
    }

    @Transactional
    public void saveUser(){
        if(saveWithValidation(user,"User Created")){
            user = new User();
        }
    }

    @Transactional
    public void deleteSelectedUsers(){
        usersSelected.forEach(this::deleteEntity);
        String message = usersSelected.size() == 1 ? "User removed" : "Users removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        user.setUserLevel(null);
        user.setIsActive(false);
        user.setEmail(null);
        user.setRegisterDate(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setPassword(null);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<UserLevel> getListUserLevel(){
        return Arrays.stream(UserLevel.values()).toList();
    }

    public List<User> getUsers(){
        return userService.getUsers();
    }

    public List<User> getUsersSelected() {
        return usersSelected;
    }

    public void setUsersSelected(List<User> usersSelected) {
        this.usersSelected = usersSelected;
    }
}
