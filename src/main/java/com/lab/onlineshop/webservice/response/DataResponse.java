package com.lab.onlineshop.webservice.response;

public record DataResponse(boolean success, String message, JsonDataResponse dataResponse) {
}

