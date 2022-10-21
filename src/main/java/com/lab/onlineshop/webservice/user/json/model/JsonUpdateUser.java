package com.lab.onlineshop.webservice.user.json.model;

public record JsonUpdateUser(Long id, String firstName, String lastName, String userName, String password, String email,
                             String userLevel, boolean isActive) {
}
