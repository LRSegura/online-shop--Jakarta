package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.User;
import com.lab.onlineshop.services.user.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

public class UserServicesImplementation implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User ORDER BY registerDate", User.class).getResultList();
    }
}
