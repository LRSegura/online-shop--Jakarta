package com.lab.onlineshop.webservice.user.json.model;

import com.lab.onlineshop.webservice.json.response.JsonDataResponse;

import java.util.List;

public record JsonUserLevel(List<String> userLevel) implements JsonDataResponse {
}
