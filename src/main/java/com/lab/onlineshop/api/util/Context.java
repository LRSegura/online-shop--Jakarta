package com.lab.onlineshop.api.util;

import com.lab.onlineshop.model.Customer;
import com.lab.onlineshop.model.User;

import java.util.Map;
import java.util.Optional;

public interface Context {
    Optional<User> getLoggedUser();
    Optional<Customer> getLoggedCustomer();
    boolean isLoggedUser();
    boolean isLoggedCustomer();

    void init();

    Map<String,Object> getRequestMapApplication();
}
