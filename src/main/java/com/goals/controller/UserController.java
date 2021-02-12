package com.goals.controller;

import com.goals.model.User;
import com.goals.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> list()
    {
        return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody final User user)
    {
        LOG.info("Saving user = " + user.toString());
        return userService.saveUser(user);
    }


}
