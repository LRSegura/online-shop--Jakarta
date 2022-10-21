package com.lab.onlineshop.webservice.product.json.model;

import com.lab.onlineshop.webservice.response.JsonDataResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JsonProducts(Long id, String description, Integer availableQuantity, BigDecimal price, Long productType, Boolean isAvailable,
                           String stock, LocalDate registerDate) implements JsonDataResponse {
}
