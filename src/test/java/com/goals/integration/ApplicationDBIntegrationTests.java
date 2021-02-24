package com.goals.integration;

import com.goals.exception.GoalDoesNotExistException;
import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.model.track.Task;
import com.goals.repository.GoalRepository;
import com.goals.repository.UserRepository;
import com.goals.repository.tree.TaskRepository;
import com.goals.repository.tree.TaskTreeRepository;
import com.goals.dao.EntityDataAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@Import(TaskTreeRepository.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationDBIntegrationTests
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTreeRepository taskTreeRepository;

    @Autowired
    private EntityManager entityManager;

    private EntityDataAccess entityDataAccess;

    private static final String EMAIL = "savageaf@thumbsup.com";

    @Before
    public void injectedComponentsAreNotNull()
    {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(goalRepository);
        Assert.assertNotNull(taskRepository);
        Assert.assertNotNull(taskTreeRepository);
        entityDataAccess = new EntityDataAccess(userRepository, goalRepository, taskRepository, taskTreeRepository);
        entityDataAccess.createEntities();
    }

    @Test
    public void testA_User_Save()
    {
        List<User> actualList = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        actualList.stream().forEach(u -> LOG.info("testUser_Save ==>> Found User ======== ::::: " + u.getFirstName()));
        Assert.assertFalse(actualList.isEmpty());

        UUID userUUID = actualList.get(0).getId();

        Optional<User> foundUserByUUID = userRepository.findById(userUUID);
        Assert.assertTrue(foundUserByUUID.isPresent());
    }

    @Test
    public void testB_Goal_Save()
    {
        List<Goal> savedGoal = StreamSupport
                .stream(goalRepository.findByUserEmail(EMAIL).spliterator(), false)
                .collect(Collectors.toList());
        savedGoal.stream().forEach(g -> LOG.info("testB_Goal_Save ==>> Found Goal [id=" + g.getId() + "] ======== ::::: "
                + g.getTitle() + " from user:" + g.getUser().getFirstName()));
        Assert.assertEquals(2, savedGoal.size());

        UUID goalId = savedGoal.get(0).getId();
        Optional<Goal> foundGoal = goalRepository.findById(goalId);
        Assert.assertTrue(foundGoal.isPresent());
    }

    @Test
    public void testC_Task_Update_Simple()
    {
        Optional<Goal> goal = entityDataAccess.getGoalForTask(EMAIL, 0);
        Task t1 = entityDataAccess.populateTaskWithRandomData(goal);
        try
        {
            LOG.info("testC_Task_Update_Simple ==>> result = " + entityDataAccess.createTask(t1, Optional.empty()));
        } catch (GoalDoesNotExistException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testD_Task_Update_WithParent()
    {
        Optional<Goal> goal = entityDataAccess.getGoalForTask(EMAIL, 0);
        Task parentTaskData = entityDataAccess.populateTaskWithRandomData(goal);
        Task childTaskData = entityDataAccess.populateTaskWithRandomData(goal);

        try
        {
            Long parentTaskId = entityDataAccess.createTask(parentTaskData, Optional.empty());
            Task parentTask = entityDataAccess.findTaskById(parentTaskId);
            LOG.info("testD_Task_Update_WithParent ==>> parentTask id=" + parentTask.getId() +" , path="+parentTask.getTaskPath());

            Long childTaskId = entityDataAccess.createTask(childTaskData, Optional.of(parentTask));
            Task childTask = entityDataAccess.findTaskById(childTaskId);
            LOG.info("testD_Task_Update_WithParent ==>> childTask id=" + childTask.getId() +" , path="+childTask.getTaskPath());

        } catch (GoalDoesNotExistException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testE_Task_SelectAll()
    {
        taskRepository.findAll().forEach(t -> LOG.info("testE_Task_SelectAll ==>> " + t));
    }
}
