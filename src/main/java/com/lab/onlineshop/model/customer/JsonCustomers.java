package com.lab.onlineshop.model.customer;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.time.LocalDate;

public record JsonCustomers(Long id, String firstName, String lastName, String userName, String password, String email,
                            String customerLevel, String address, String phoneNumber, LocalDate registerDate,
                            boolean selectedToDelete) implements JsonDataResponse {
}
