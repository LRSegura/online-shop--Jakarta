package com.lab.onlineshop.webservice.product.type.json.model;

import com.lab.onlineshop.webservice.json.JsonDelete;

import java.util.List;

public record JsonDeleteProductType(List<Long> productsTypeId) implements JsonDelete {
    @Override
    public List<Long> getIdList() {
        return productsTypeId;
    }
}
