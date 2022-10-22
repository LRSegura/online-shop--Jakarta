package com.lab.onlineshop.webservice.login.json.model;

import com.lab.onlineshop.webservice.json.response.JsonDataResponse;

public record JsonLoginResponse(boolean isUser, boolean isCustomer, String name) implements JsonDataResponse {
    public JsonLoginResponse(boolean isUser, boolean isCustomer){
        this(isUser,isCustomer,null);
    }
}
