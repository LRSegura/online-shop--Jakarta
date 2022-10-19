package com.lab.onlineshop.webservice;

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
