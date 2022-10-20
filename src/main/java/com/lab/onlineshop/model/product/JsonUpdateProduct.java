package com.lab.onlineshop.model.product;

import java.math.BigDecimal;

public record JsonUpdateProduct(Long id, String description, Integer availableQuantity, BigDecimal price, Long productType,
                                Boolean isAvailable, String stock) {
}
