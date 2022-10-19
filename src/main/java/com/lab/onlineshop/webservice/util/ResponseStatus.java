package com.lab.onlineshop.webservice.util;

public enum ResponseStatus {
    SUCCESS(true),
    FAILED(false);
    private final boolean status;
    ResponseStatus(boolean status){
        this.status =status;
    }

    public boolean getStatus() {
        return status;
    }
}
