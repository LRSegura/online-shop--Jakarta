package com.lab.onlineshop.webservice.json.response;

public record DataResponse(boolean success, String message, JsonDataResponse dataResponse) {
}

