package com.lab.onlineshop.model.user;

public enum UserLevel {
    ADMIN("Admin"),
    USER("User");

    private final String description;
    UserLevel(String description){
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public static UserLevel getUserLevel(String description) {
        for (var value: values()) {
            if(value.description.equalsIgnoreCase(description)){
                return value;
            }
        }
        throw new IllegalArgumentException("Wrong value to get User Level enum");
    }
}
