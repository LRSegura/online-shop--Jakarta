package com.lab.onlineshop.webservice.customer.json.model;

import com.lab.onlineshop.webservice.json.JsonDelete;

import java.util.List;

public record JsonDeleteCustomer(List<Long> customersId) implements JsonDelete {
    @Override
    public List<Long> getIdList() {
        return customersId;
    }
}
