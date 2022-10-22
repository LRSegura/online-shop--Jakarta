package com.lab.onlineshop.webservice.customer.json.model;

import com.lab.onlineshop.webservice.json.response.JsonDataResponse;

import java.time.LocalDate;

public record JsonCustomers(Long id, String firstName, String lastName, String userName, String password, String email,
                            String customerLevel, String address, String phoneNumber, LocalDate registerDate,
                            boolean selectedToDelete) implements JsonDataResponse {
}
