package com.lab.onlineshop.webservice.product.json.model;

import java.math.BigDecimal;

public record JsonAddProduct(String description, Integer availableQuantity, BigDecimal price, Long productType, Boolean isAvailable,
                             String stock) {
}
