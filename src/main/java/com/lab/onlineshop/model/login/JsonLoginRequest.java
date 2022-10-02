package com.lab.onlineshop.model.login;

import java.util.Objects;

public record JsonLogin(String userName, String password) {
    public JsonLogin {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(password);
    }

}
