package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserServicesImplementation implements UserService {

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
}
