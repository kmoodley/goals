package com.goals.dao;

import com.goals.exception.GoalDoesNotExistException;
import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.model.track.Task;
import com.goals.repository.GoalRepository;
import com.goals.repository.UserRepository;
import com.goals.repository.tree.TaskRepository;
import com.goals.repository.tree.TaskTreeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EntityDataAccess
{
    private UserRepository userRepository;
    private GoalRepository goalRepository;
    private TaskRepository taskRepository;
    private TaskTreeRepository taskTreeRepository;

    private static final Logger LOG = LogManager.getLogger();


    public EntityDataAccess(UserRepository userRepository, GoalRepository goalRepository,
                            TaskRepository taskRepository, TaskTreeRepository taskTreeRepository)
    {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.taskRepository = taskRepository;
        this.taskTreeRepository = taskTreeRepository;
    }

    public void createEntities()
    {
        saveUser("John", "Edwards", "forward@thinking.com");
        saveUser("Fred", "Savage", "savageaf@thumbsup.com");

        //Create Goals with saved user above
        User foundUser = userRepository.findByEmail("savageaf@thumbsup.com");
        Assert.assertNotNull(foundUser);
        saveGoal(foundUser, "Goal that I planned yesterday", LocalDate.of(2021, 6, 1));
        saveGoal(foundUser, "Another goal, not that cool", LocalDate.of(2022, 3, 23));
    }

    private void saveUser(String name, String surname, String email)
    {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);
        userRepository.save(user);
    }

    private void saveGoal(User foundUser, String s, LocalDate date)
    {
        Goal goal1 = new Goal();
        goal1.setTitle(s);
        goal1.setDescription("Empty description");
        goal1.setDueDate(date);
        goal1.setProgress(0.00);
        goal1.setUser(foundUser);
        goalRepository.saveAndFlush(goal1);
    }

    public Optional<Goal> getGoalForTask(String email, int index)
    {
        List<Goal> savedGoal = StreamSupport
                .stream(goalRepository.findByUserEmail(email).spliterator(), false)
                .collect(Collectors.toList());
        Assert.assertFalse(savedGoal.isEmpty());
        UUID goalUUID = savedGoal.get(index).getId();
        Optional<Goal> goal = goalRepository.findById(goalUUID);
        Assert.assertTrue(goal.isPresent());
        return goal;
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
            pathText = parentId + "." + pathText;
        }

        taskToUpdate.setTaskPath(pathText);
        taskTreeRepository.updateTask(taskToUpdate);
    }

    public Task findTaskById(Long taskId)
    {
        Optional<Task> foundTask = taskRepository.findById(taskId);
        Assert.assertTrue(foundTask.isPresent());
        return foundTask.get();
    }

    /**
     * Update Task with the initial necessary details e.g. Title, Due Date etc
     *
     * @return task id
     */
    private Long persistTask(Task task) throws GoalDoesNotExistException
    {
        Long result = taskTreeRepository.insertTask(task);
        return result;
    }

    public Task populateTaskWithRandomData(Optional<Goal> goal)
    {
        Task task = new Task();
        task.setTitle("Random Task title " + LocalTime.now());
        task.setProgress(0.00);
        task.setDueDate(LocalDate.of(2021, 2, 20));
        task.setGoal(goal.get());
        task.setTaskPath("");
        return task;
    }

    public Long createTask(Task task, Optional<Task> parentTask) throws GoalDoesNotExistException
    {
        Long result = persistTask(task);
        updateTaskPath(result, parentTask);
        return result;
    }
}
