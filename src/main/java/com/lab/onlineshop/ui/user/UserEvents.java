package com.lab.onlineshop.ui.user;

import com.lab.onlineshop.api.exceptions.ApplicationBusinessException;
import com.lab.onlineshop.api.exceptions.EntityFieldException;
import com.lab.onlineshop.api.exceptions.message.ErrorMessage;
import com.lab.onlineshop.model.user.User;
import com.lab.onlineshop.services.user.UserService;
import com.lab.onlineshop.ui.AbstractEntityEvents;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserEvents extends AbstractEntityEvents<User> {

    @Inject
    private UserService userService;

    @Override
    public void makeValidationBeforeAction(User user) throws EntityFieldException {
        super.makeValidationBeforeAction(user);
        userService.getUser(user.getUserName()).ifPresent(value -> {
            throw new ApplicationBusinessException(ErrorMessage.USERNAME_IN_USE);
        });
    }

    public UserService getUserService() {
        return userService;
    }
}
