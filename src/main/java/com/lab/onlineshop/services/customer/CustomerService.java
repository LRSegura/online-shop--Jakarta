package com.lab.onlineshop.services.customer;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.Service;

import java.util.List;

public interface CustomerService extends Service {
    List<Customer> getCustomers();

}
