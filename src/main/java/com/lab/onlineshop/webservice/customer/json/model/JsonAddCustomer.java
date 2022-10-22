package com.lab.onlineshop.webservice.customer.json.model;

import com.lab.onlineshop.webservice.json.JsonAdd;

public record JsonAddCustomer(String firstName, String lastName, String userName, String password, String email,
                              String customerLevel, String address, String phoneNumber) implements JsonAdd {
}
