package com.goals.controller;

import com.goals.exception.GoalDoesNotExistException;
import com.goals.exception.TaskDoesNotExistException;
import com.goals.model.track.Task;
import com.goals.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private TaskService taskService;

    @GetMapping
    @RequestMapping("{id}")
    public List<Task> listTasks(@PathVariable UUID id) throws GoalDoesNotExistException
    {
        LOG.info("list: Getting tasks for goal with id : " + id);
        return taskService.getTasks(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/save_task")
    public Task createGoalTask(@RequestBody final Task task,
                               @RequestParam UUID uuid,
                               @RequestParam Long taskParentId) throws GoalDoesNotExistException, TaskDoesNotExistException
    {
        return taskService.createTask(uuid, task, taskParentId);
    }

}
