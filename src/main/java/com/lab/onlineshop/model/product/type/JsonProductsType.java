package com.lab.onlineshop.model.product.type;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JsonProductsType(Long id, String description, LocalDate registerDate) implements JsonDataResponse {
}
