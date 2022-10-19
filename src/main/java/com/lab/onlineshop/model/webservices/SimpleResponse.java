package com.lab.onlineshop.model.webservices;

import java.util.Collections;
import java.util.List;

public record SimpleResponse(boolean success, String message, List<String> messages) {
    public SimpleResponse(boolean success, String message){
        this(success, message, Collections.emptyList());
    }
}
