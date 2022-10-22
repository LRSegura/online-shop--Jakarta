package com.lab.onlineshop.webservice.user.json.model;

import com.lab.onlineshop.webservice.json.JsonDelete;

import java.util.List;

public record JsonDeleteUsers(List<Long> usersId) implements JsonDelete {
    @Override
    public List<Long> getIdList() {
        return usersId;
    }
}
