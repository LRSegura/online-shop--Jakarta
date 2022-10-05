package com.lab.onlineshop.model.user;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.util.List;

public record JsonUserLevelResponse(List<String> userLevel) implements JsonDataResponse {
}
