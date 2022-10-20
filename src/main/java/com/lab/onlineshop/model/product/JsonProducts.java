package com.lab.onlineshop.model.product;

import com.lab.onlineshop.model.webservices.JsonDataResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JsonProducts(Long id, String description, Integer availableQuantity, BigDecimal price, Long productType, Boolean isAvailable,
                           String stock, LocalDate registerDate) implements JsonDataResponse {
}
