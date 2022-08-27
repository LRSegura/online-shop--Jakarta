package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.user.User;

import java.util.List;
import java.util.Optional;

public sealed interface UserService extends Service permits UserServicesImplementation{

    List<User> getUsers();
    Optional<User> getUser(String userName, String password);
    Optional<User> getUser(String userName);

}
