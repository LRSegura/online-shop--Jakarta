package com.lab.onlineshop.api.exceptions;

import com.lab.onlineshop.api.exceptions.message.ErrorMessage;

import java.util.function.BiFunction;

public class ApplicationBusinessException extends RuntimeException {

    public ApplicationBusinessException(ErrorMessage genericMessage){
        super(genericMessage.getDescription());
    }

    public ApplicationBusinessException(ErrorMessage genericMessage, CharSequence detailMessage){
        super(genericMessage + " " + detailMessage);
    }
}
