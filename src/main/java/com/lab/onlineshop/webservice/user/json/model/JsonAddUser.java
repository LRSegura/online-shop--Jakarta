package com.lab.onlineshop.webservice.user.json.model;

public record JsonAddUser(String firstName, String lastName, String userName, String password, String email,
                          String userLevel, boolean isActive) {
}
