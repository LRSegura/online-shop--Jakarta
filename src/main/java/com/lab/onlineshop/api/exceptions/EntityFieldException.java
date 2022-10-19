package com.lab.onlineshop.api.exceptions;

import com.lab.onlineshop.api.exceptions.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityFieldException extends Exception {

    private final List<String> specificErrorList = new ArrayList<>();

    public EntityFieldException(String message){
        super(message);
    }

    public List<String> getSpecificErrorList() {
        return specificErrorList;
    }

    public void throwIfThereIsSpecificError() throws EntityFieldException {
        if(!specificErrorList.isEmpty()){
            throw this;
        }
    }
}
