package com.lab.onlineshop.model.product;

import java.math.BigDecimal;

public record ProductRecord(Long id, Integer available, String description, BigDecimal price, String productType, String stock) {
}
