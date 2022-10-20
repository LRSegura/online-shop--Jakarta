package com.lab.onlineshop.model.customer;

public record JsonAddCustomer(String firstName, String lastName, String userName, String password, String email,
                              String customerLevel, String address, String phoneNumber) {
}