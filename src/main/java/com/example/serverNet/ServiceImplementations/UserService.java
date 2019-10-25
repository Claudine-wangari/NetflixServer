package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User addUser(User user);
}
