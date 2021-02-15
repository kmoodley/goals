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

    public List<Goal> getGoals(String email) throws UserDoesNotExistException
    {
        List<Goal> foundGoalsForUser = goalRepository.findByUserEmail(email);

        if (foundGoalsForUser.isEmpty())
        {
            throw new UserDoesNotExistException("User with id " + email + " does not exist!");
        }

        foundGoalsForUser.forEach(g -> LOG.info("\tFound Goals :: " + g));
        return goalRepository.findByUserEmail(email);
    }

    public Goal saveGoal(String email, Goal goal) throws UserDoesNotExistException
    {
        User user = userRepository.findByEmail(email);
        if (user == null)
        {
            throw new UserDoesNotExistException("User with id " + email + " does not exist!");
        }
        LOG.info("saveGoal -> Found user: " + user + ", saving goal:"+goal.getTitle());
        goal.setUser(user);
        return goalRepository.save(goal);
    }
}
