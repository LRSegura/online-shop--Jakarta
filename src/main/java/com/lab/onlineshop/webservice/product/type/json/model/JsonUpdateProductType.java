package com.lab.onlineshop.webservice.product.type.json.model;

import com.lab.onlineshop.webservice.json.JsonUpdate;

public record JsonUpdateProductType(Long id, String description) implements JsonUpdate {
}
