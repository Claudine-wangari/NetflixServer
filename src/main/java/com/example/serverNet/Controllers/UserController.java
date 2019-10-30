package com.example.serverNet.Controllers;


import com.example.serverNet.Errorhandlers.AlreadyExistsException;
import com.example.serverNet.Errorhandlers.InvalidInputException;
import com.example.serverNet.Models.User;
import com.example.serverNet.Repositories.UserRepository;
import com.example.serverNet.ServiceImplementations.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="users")
public class UserController
{
    private final UserRepository userRepository;

    public UserController( UserRepository userRepository)
    {
        this.userRepository = userRepository;

    }

    //Listing all users.
    @GetMapping
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    //Registering a User
    @PostMapping
    public User addUser(@RequestBody User user)
    {
        if(user.getId_number() == null ||
                user.getUsername() == null)
            throw new InvalidInputException("Id Number or Username missing. Ensure the fields are filled");

        try
        {
            return userRepository.save(user);
        }
        catch (AlreadyExistsException exception)
        {
            throw new AlreadyExistsException("User Id Already Exists");
        }
    }


}
