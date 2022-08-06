package com.lab.onlineshop.services.user;

import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.User;

import java.util.List;

public interface UserService extends Service {

    List<User> getUsers();

}
