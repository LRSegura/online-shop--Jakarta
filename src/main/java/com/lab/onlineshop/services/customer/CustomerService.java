package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends Service {
    List<Customer> getCustomers();
    Optional<Customer> getCustomer(String userName, String password);

    Optional<Customer> getCustomer(String userName);

}
