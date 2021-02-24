package com.goals.service;

import com.goals.exception.UserDoesNotExistException;
import com.goals.model.User;
import com.goals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User findUserByUUID(UUID uuid) throws UserDoesNotExistException
    {
        Optional<User> userFoundById = userRepository.findById(uuid);
        if (!userFoundById.isPresent())
        {
            throw new UserDoesNotExistException("User not found id=" + uuid);
        }

        return userFoundById.get();
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public Collection<User> getUsers()
    {
        return userRepository.findAll();
    }

    public User saveUser(User user)
    {
        return userRepository.saveAndFlush(user);
    }

    public void deleteAll()
    {
        userRepository.deleteAll();
    }

    public void deleteUsers(List<User> users)
    {
        userRepository.deleteInBatch(users);
    }
}
