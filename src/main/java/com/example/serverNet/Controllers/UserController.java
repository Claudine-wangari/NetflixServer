package com.example.serverNet.Controllers;


import com.example.serverNet.Errorhandlers.AlreadyExistsException;
import com.example.serverNet.Errorhandlers.InvalidInputException;
import com.example.serverNet.Models.User;
import com.example.serverNet.ServiceImplementations.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Listing all users.
    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    //Registering a User
    @PostMapping
    public User addUser(@RequestBody User user)
    {
        if(user.getId() == null ||
                user.getUsername() == null)
            throw new InvalidInputException("Id Number or Username missing. Ensure the fields are filled");

        try {
            return userService.addUser(user);
        }catch (AlreadyExistsException exception){
            throw new AlreadyExistsException("User Id Already Exists");
        }
    }


}
