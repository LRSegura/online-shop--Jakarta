package com.lab.onlineshop.webservice.user.json.model;

import com.lab.onlineshop.webservice.json.JsonAdd;

public record JsonAddUser(String firstName, String lastName, String userName, String password, String email,
                          String userLevel, boolean isActive) implements JsonAdd {
}
