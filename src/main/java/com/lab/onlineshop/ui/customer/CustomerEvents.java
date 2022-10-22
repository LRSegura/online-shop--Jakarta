package com.lab.onlineshop.ui.customer;

import com.lab.onlineshop.api.exceptions.ApplicationBusinessException;
import com.lab.onlineshop.api.exceptions.EntityFieldException;
import com.lab.onlineshop.api.exceptions.message.ErrorMessage;
import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.ui.AbstractEntityEvents;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CustomerEvents extends AbstractEntityEvents<Customer> {

    @Inject
    private CustomerService customerService;

    @Override
    public void makeValidationBeforeAction(Customer customer) throws EntityFieldException {
        super.makeValidationBeforeAction(customer);
        customerService.getCustomer(customer.getUserName()).ifPresent(value -> {
            throw new ApplicationBusinessException(ErrorMessage.USERNAME_IN_USE);
        });
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}
