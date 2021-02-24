package com.goals.helpers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.goals.model.User;
import com.goals.model.track.Goal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator
{
    private final FakeValuesService fakeValuesService;
    private final Faker faker;

    public DataGenerator()
    {
        fakeValuesService = new FakeValuesService(
                new Locale("en-ZA"), new RandomService());
        faker = new Faker();
    }

    public User createUser()
    {
        User user = new User();
        String email = fakeValuesService.bothify("????##@gmail.com");
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(email);
        user.setTitle(faker.name().title());
        user.setPhone(faker.phoneNumber().cellPhone());
        return user;
    }

    public Goal createGoal()
    {
        Goal goal = new Goal();
        goal.setTitle(faker.lorem().sentence(5));
        goal.setDescription(faker.lorem().sentence());
        goal.setDueDate(getFutureDate());
        goal.setProgress(faker.number().randomDouble(2,1,99));
        return goal;
    }

    public LocalDate getFutureDate() {
        Date dueDate = faker.date().future(1, TimeUnit.DAYS);
        return LocalDate.ofInstant(
                dueDate.toInstant(), ZoneId.systemDefault());
    }
}
