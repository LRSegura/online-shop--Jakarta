package com.lab.onlineshop.model.login;

import java.util.Objects;

public record JsonLoginRequest(String userName, String password) {
    public JsonLoginRequest {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(password);
    }

}
