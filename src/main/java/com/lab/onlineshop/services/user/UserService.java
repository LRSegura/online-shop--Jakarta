package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {

    List<User> getUsers();
    Optional<User> getUser(String userName, String password);
    Optional<User> getUser(String userName);

}
