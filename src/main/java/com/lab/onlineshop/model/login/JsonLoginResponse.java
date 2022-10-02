package com.lab.onlineshop.model.login;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

public record JsonLoginResponse(boolean isUser, boolean isCustomer, String name) implements JsonDataResponse {
}
