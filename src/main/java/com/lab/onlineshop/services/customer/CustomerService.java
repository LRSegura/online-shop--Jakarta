package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.model.Service;

import java.util.List;
import java.util.Optional;

public sealed interface CustomerService extends Service permits CustomerServiceImplementation{
    List<Customer> getCustomers();
    Optional<Customer> getCustomer(String userName, String password);

    Optional<Customer> getCustomer(String userName);

}
