package com.lab.onlineshop.webservice.product.json.model;

import com.lab.onlineshop.webservice.json.JsonUpdate;

import java.math.BigDecimal;

public record JsonUpdateProduct(Long id, String description, Integer availableQuantity, BigDecimal price, Long productType,
                                Boolean isAvailable, String stock) implements JsonUpdate {
}
