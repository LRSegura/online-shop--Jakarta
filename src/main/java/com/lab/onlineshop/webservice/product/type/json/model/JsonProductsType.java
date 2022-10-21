package com.lab.onlineshop.webservice.product.type.json.model;

import com.lab.onlineshop.webservice.response.JsonDataResponse;

import java.time.LocalDate;

public record JsonProductsType(Long id, String description, LocalDate registerDate) implements JsonDataResponse {
}
