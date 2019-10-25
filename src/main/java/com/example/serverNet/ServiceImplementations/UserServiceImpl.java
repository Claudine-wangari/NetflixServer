package com.example.serverNet.ServiceImplementations;

import com.example.serverNet.Errorhandlers.AlreadyExistsException;
import com.example.serverNet.Models.User;
import com.example.serverNet.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //Function gets all users registered.
    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

//    Function validates that a new user being registered is not in the system
//    And if the user exists an AlreadyExists Exception is thrown.
//    If not then the user is saved in the repository.
    @Override
    public User addUser(User user) throws AlreadyExistsException
    {
        Optional<User> presentUser = userRepository.findById(user.getId());

        if(presentUser != null)
        {
            throw new AlreadyExistsException ("User exists");
        }
            return userRepository.save(user);
    }


}
