package com.lab.onlineshop.model.user;

public record JsonUserRequest(String firstName, String lastName, String userName, String password, String email, String userLevel, boolean isActive) {
}
