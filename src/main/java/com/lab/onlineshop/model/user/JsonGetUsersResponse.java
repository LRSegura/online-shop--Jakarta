package com.lab.onlineshop.model.user;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.time.LocalDate;

public record JsonGetUsersResponse(Long id, String firstName, String lastName, String userName, String userLevel, boolean isActive,
                                   LocalDate registerDate, boolean selectedToDelete) implements JsonDataResponse {
}
