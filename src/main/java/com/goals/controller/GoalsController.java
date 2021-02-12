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
    @RequestMapping("{id}")
    public List<Goal> list(@PathVariable Long id)
    {
        return goalService.getGoals(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/save/{id}")
    public Goal createGoal(@PathVariable Long id, @RequestBody final Goal goal) throws UserDoesNotExistException
    {
        return goalService.saveGoal(id, goal);
    }

}
