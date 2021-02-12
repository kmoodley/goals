package com.goals.service;

import com.goals.exception.UserDoesNotExistException;
import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.repository.GoalRepository;
import com.goals.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Goal> getGoals(Long userId)
    {
        return goalRepository.findAll();
    }

    public Goal saveGoal(Long userId, Goal goal) throws UserDoesNotExistException
    {
        User user = userRepository.getOne(userId);
        if (user == null)
        {
            throw new UserDoesNotExistException("User with id " + userId + " does not exist!");
        }

        goal.setUser(user);
        return goalRepository.save(goal);
    }
}
