package com.lab.onlineshop.webservice.product.json.model;

import java.math.BigDecimal;

public record JsonUpdateProduct(Long id, String description, Integer availableQuantity, BigDecimal price, Long productType,
                                Boolean isAvailable, String stock) {
}
