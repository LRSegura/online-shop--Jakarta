package com.lab.onlineshop.webservice.product.json.model;

import com.lab.onlineshop.webservice.json.JsonDelete;

import java.util.List;

public record JsonDeleteProduct(List<Long> productsId) implements JsonDelete {
    @Override
    public List<Long> getIdList() {
        return productsId;
    }
}
