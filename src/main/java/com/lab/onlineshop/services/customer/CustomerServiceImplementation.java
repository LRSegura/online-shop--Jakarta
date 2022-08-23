package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;

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
}
