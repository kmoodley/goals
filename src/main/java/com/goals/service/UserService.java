package com.goals.service;

import com.goals.model.User;
import com.goals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public Collection<User> getUsers()
    {
        return userRepository.findAll();
    }

    public User saveUser(User user)
    {
        return userRepository.saveAndFlush(user);
    }

}
