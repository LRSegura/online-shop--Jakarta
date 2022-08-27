package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.user.User;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public final class UserServicesImplementation implements UserService {

    @EJB
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }
    @Override
    public List<User> getUsers() {
        return getEntityManager().createQuery("FROM User ORDER BY registerDate", User.class).getResultList();
    }

    @Override
    public Optional<User> getUser(String userName, String password) {
        return getEntityManager().createQuery("from User where userName = :userName and password = : password", User.class)
                .setParameter("userName",userName).setParameter("password",password)
                .getResultList().stream().findAny();
    }

    @Override
    public Optional<User> getUser(String userName) {
        return getEntityManager().createQuery("from User where userName = :userName", User.class)
                .setParameter("userName",userName)
                .getResultList().stream().findAny();
    }
}
