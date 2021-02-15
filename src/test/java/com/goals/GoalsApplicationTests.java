package com.goals;

import com.goals.model.User;
import com.goals.model.track.Goal;
import com.goals.repository.GoalRepository;
import com.goals.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoalsApplicationTests
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Before
    public void injectedComponentsAreNotNull()
    {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testA_User_Save()
    {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Edwards");
        user.setEmail("forward@thinking.com");
        userRepository.save(user);

        List<User> actualList = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        actualList.stream().forEach(u -> LOG.info("testUser_Save ==>> Found User ======== ::::: " + u.getFirstName()));
        Assert.assertEquals(2, actualList.size());
    }

    @Test
    public void testB_Goal_Save()
    {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Edwards");
        String email = "forward@thinking.com";
        user.setEmail(email);
        userRepository.save(user);

        User foundUser = userRepository.findByEmail(email);
        Assert.assertNotNull(foundUser);
        LOG.info("testGoal_Save ==>> Found user :: " + foundUser.toString() +" , save goal!");

        Goal goal = new Goal();
        goal.setTitle("Empty title");
        goal.setDescription("Empty description");
        goal.setDueDate(LocalDate.of(2021, 6,1));
        goal.setProgress(0.00);
        goal.setUser(foundUser);
        goalRepository.saveAndFlush(goal);

        List<Goal> savedGoal = StreamSupport
                .stream(goalRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        savedGoal.stream().forEach(g -> LOG.info("testGoal_Save ==>> Found Goal ======== ::::: "
                + g.getTitle() +" from user:"+g.getUser().getFirstName()));
    }
}
