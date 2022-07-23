package com.lab.onlineshop.services;

import com.lab.onlineshop.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

public class UserServicesImplementation implements UserService, Serializable {

//    private static UserServicesImplementation USER_SERVICES_IMPLEMENTATION;

//    private UserServicesImplementation(){
//
//    }

//    public static UserServicesImplementation getInstance(){
//        if(USER_SERVICES_IMPLEMENTATION != null){
//            return USER_SERVICES_IMPLEMENTATION;
//        }
//        USER_SERVICES_IMPLEMENTATION = new UserServicesImplementation();
//        return USER_SERVICES_IMPLEMENTATION;
//    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User ORDER BY registerDate", User.class).getResultList();
    }
}
