package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImplementation implements CustomerService {

    @EJB
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }

    @Override
    public List<Customer> getCustomers() {
        return getEntityManager().createQuery("FROM Customer order by registerDate desc ", Customer.class).getResultList();
    }
    @Override
    public Optional<Customer> getCustomer(String userName, String password) {
        return getEntityManager().createQuery("from Customer where userName = :userName and password = : password", Customer.class)
                .setParameter("userName",userName).setParameter("password",password)
                .getResultList().stream().findAny();
    }

    @Override
    public Optional<Customer> getCustomer(String userName) {
        return getEntityManager().createQuery("from Customer where userName = :userName", Customer.class)
                .setParameter("userName",userName)
                .getResultList().stream().findAny();
    }
}
