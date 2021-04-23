package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    //Contain all the business logic and communicate to data base
   @Autowired
    private UserRepository userRepository;

    public User login(User user)
    {
        User existingUser = userRepository.checkCredentials(user.getUsername(),user.getPassword());
        if(existingUser == null)
            return null;
        else
            return existingUser;
     }

    public void registerUser(User newUser){
        userRepository.registerUser(newUser);
    }
}
