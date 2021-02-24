package com.goals.service;

import com.goals.exception.GoalDoesNotExistException;
import com.goals.exception.TaskDoesNotExistException;
import com.goals.model.track.Task;
import com.goals.repository.GoalRepository;
import com.goals.repository.tree.TaskRepository;
import com.goals.repository.tree.TaskTreeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService
{
    private static final Logger LOG = LogManager.getLogger();
    public static final String PATH_SPLIT = ".";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTreeRepository taskTreeRepository;

    @Autowired
    private GoalRepository goalRepository;

    public List<Task> getTasks(UUID goalId)
    {
        List<Task> foundTasksForGoal = taskRepository.findByGoalId(goalId);
        return foundTasksForGoal;
    }

    public Task createTask(UUID goalId, Task task , Long parentTaskId) throws GoalDoesNotExistException,TaskDoesNotExistException
    {
        task.setGoal(goalRepository.findById(goalId)
                .orElseThrow(GoalDoesNotExistException::new));
        Long insertedTaskId = taskTreeRepository.insertTask(task);
        updateTaskPath(insertedTaskId, taskRepository.findById(parentTaskId));
        return taskRepository.findById(insertedTaskId).orElseThrow(TaskDoesNotExistException::new);
    }

    /**
     * Will update the task path with the correct ancestry information
     *
     * @param result
     * @param parentTask
     */
    public void updateTaskPath(Long result, Optional<Task> parentTask)
    {
        Optional<Task> optionalTask = taskRepository.findById(result);
        Task taskToUpdate = optionalTask.get();

        Long id = taskToUpdate.getId();
        String pathText = String.valueOf(id);

        if (parentTask.isPresent())
        {
            Long parentId = parentTask.get().getId();
            pathText = parentId + PATH_SPLIT + pathText;
        }

        taskToUpdate.setTaskPath(pathText);
        taskTreeRepository.updateTask(taskToUpdate);
    }
}
