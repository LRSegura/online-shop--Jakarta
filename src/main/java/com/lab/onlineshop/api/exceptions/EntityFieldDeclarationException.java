package com.lab.onlineshop.api.exceptions;

import com.lab.onlineshop.api.exceptions.message.ErrorMessage;

public class EntityFieldDeclarationException extends EntityFieldException {
    public EntityFieldDeclarationException() {
        super(ErrorMessage.FIELD_DECLARATION.getDescription());
    }

    public EntityFieldDeclarationException(String message) {
        super(message);
    }
}
