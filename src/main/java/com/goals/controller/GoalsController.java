package com.goals.controller;

import com.goals.exception.UserDoesNotExistException;
import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.service.GoalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalsController
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private GoalService goalService;

    @GetMapping
    public List<Goal> list(@RequestParam String email) throws UserDoesNotExistException
    {
        LOG.info("Getting goals for user with email : " + email);
        return goalService.getGoals(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/save")
    public Goal createGoal(@RequestParam String email, @RequestBody final Goal goal) throws UserDoesNotExistException
    {
        LOG.info("createGoal -> POST REQUEST : " + goal.toString() +", user email : "+ email);
        return goalService.saveGoal(email, goal);
    }

}
