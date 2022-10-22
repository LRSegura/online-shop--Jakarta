package com.lab.onlineshop.webservice.customer.json.model;

import com.lab.onlineshop.webservice.json.JsonUpdate;

public record JsonUpdateCustomer(Long id, String firstName, String lastName, String userName, String password, String email,
                                 String customerLevel, String address, String phoneNumber) implements JsonUpdate {
}
