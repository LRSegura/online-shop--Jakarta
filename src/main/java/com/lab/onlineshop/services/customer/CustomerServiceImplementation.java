package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.services.customer.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

public class CustomerServiceImplementation implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> getCustomers() {
        return entityManager.createQuery("FROM Customer order by registerDate desc ", Customer.class).getResultList();
    }
}
