package com.goals.service;

import com.goals.exception.UserDoesNotExistException;
import com.goals.helpers.DataGenerator;
import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.repository.tree.TaskTreeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Import(TaskTreeRepository.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestApplicationServices
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private GoalService goalService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    private DataGenerator dataGenerator;
    private List<User> savedUsers = new ArrayList<>();
    private List<Goal> savedGoals = new ArrayList<>();

    @Before
    public void injectedComponentsAreNotNull()
    {
        Assert.assertNotNull(goalService);
        Assert.assertNotNull(taskService);
        Assert.assertNotNull(userService);
        dataGenerator = new DataGenerator();
    }

    @Test
    public void testA_UserSave()
    {
        createUsers(3);
        savedUsers.forEach(u ->
                {
                    userService.saveUser(u);
                    LOG.info("testA_UserSave : saved user = " + displayUser(u));
                }
        );
    }

    @Test
    public void testB_GoalSave() throws UserDoesNotExistException
    {
        User user = dataGenerator.createUser();
        savedUsers.add(user);
        userService.saveUser(user);
        createGoals(4, user.getEmail());
        savedGoals.addAll(goalService.getGoals(user.getEmail()));

        savedGoals.forEach(g ->
                LOG.info("testB_GoalSave : goal = " + displayGoal(g))
        );
    }

    private void createUsers(int num)
    {
        for (int i = 0; i <= num; i++)
        {
            savedUsers.add(dataGenerator.createUser());
        }
    }

    private void createGoals(int num, String email) throws UserDoesNotExistException
    {
        for (int i = 0; i <= num; i++)
        {
            goalService.saveGoal(email, dataGenerator.createGoal());
        }
    }

    private String displayUser(User u)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(u.getId()).append(",");
        sb.append(u.getFirstName()).append(" ").append(u.getLastName()).append(",");
        sb.append(u.getEmail());
        return sb.toString();
    }

    private String displayGoal(Goal g)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(g.getId()).append(",");
        sb.append(g.getTitle()).append(",");
        sb.append(g.getDueDate()).append(" - ").append(g.getProgress());
        return sb.toString();
    }

    @After
    public void doYourOneTimeTeardown()
    {
        goalService.deleteAll();
        userService.deleteAll();
    }
}
