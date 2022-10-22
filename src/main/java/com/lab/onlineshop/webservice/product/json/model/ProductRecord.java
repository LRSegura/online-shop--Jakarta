package com.lab.onlineshop.webservice.product.json.model;

import java.math.BigDecimal;

public record ProductRecord(Long id, Integer available, String description, BigDecimal price, String productType, String stock) {
}
