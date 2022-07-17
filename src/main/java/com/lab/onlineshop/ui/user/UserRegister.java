package com.lab.onlineshop.ui.user;

import com.lab.onlineshop.model.User;
import com.lab.onlineshop.model.UserLevel;
import com.lab.onlineshop.ui.FormsEvents;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class UserRegister extends FormsEvents<User> {
    @PersistenceContext
    private EntityManager entityManager;

    private User user = new User();

    public User getUser(){
        return user;
    }

    @Transactional
    public void saveUser(){
        if(saveWithValidation(user,"User Created")){
            user = new User();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<UserLevel> getListUserLevel(){
        return Arrays.stream(UserLevel.values()).toList();
    }
}
