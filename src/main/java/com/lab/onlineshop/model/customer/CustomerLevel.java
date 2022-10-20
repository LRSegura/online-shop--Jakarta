package com.lab.onlineshop.model.customer;

import com.lab.onlineshop.model.user.UserLevel;

public enum CustomerLevel {
    PREMIUM("Premium"),
    STANDARD("Standard");

    private final String description;
    CustomerLevel(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static CustomerLevel getCustomerLevel(String description) {
        for (var value: values()) {
            if(value.description.equalsIgnoreCase(description)){
                return value;
            }
        }
        throw new IllegalArgumentException("Wrong value to get User Level enum");
    }
}
