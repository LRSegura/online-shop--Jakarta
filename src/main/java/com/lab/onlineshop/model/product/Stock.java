package com.lab.onlineshop.model.product;

public enum Stock {

    IN_STOCK("In Stock"),
    LOW_STOCK("Low Stock"),
    OUT_STOCK("Out Stock");

    private final String description;

    private Stock(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public static Stock getStock(int count){

        if(count == 0){
            return OUT_STOCK;
        }
        if(count < 10){
            return LOW_STOCK;
        }
        return IN_STOCK;
    }

}
