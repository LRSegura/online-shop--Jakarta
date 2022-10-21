package com.lab.onlineshop.webservice.user.json.model;

import com.lab.onlineshop.webservice.response.JsonDataResponse;

import java.time.LocalDate;

public record JsonUsers(Long id, String firstName, String lastName, String userName, String email, String password,
                        String userLevel, boolean isActive, LocalDate registerDate, boolean selectedToDelete) implements JsonDataResponse {
}
