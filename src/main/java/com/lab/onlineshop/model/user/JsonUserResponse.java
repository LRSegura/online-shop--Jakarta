package com.lab.onlineshop.model.user;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.time.LocalDate;

public record JsonUserResponse(Long id, String firstName, String lastName, String userName, UserLevel userLevel, boolean isActive,
                               LocalDate registerDate) implements JsonDataResponse {
}
