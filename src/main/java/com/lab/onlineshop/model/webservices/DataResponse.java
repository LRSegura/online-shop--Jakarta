package com.lab.onlineshop.model.webservices;

public record DataResponse(boolean success, String message, JsonDataResponse dataResponse) {
}

