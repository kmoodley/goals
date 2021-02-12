package com.goals.model.track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

/**
 * Task Component that is a single task belonging to a Task Group
 *
 * @author kemendran.moodley
 */
public class Task extends TaskComponent
{
    private static final Logger LOG = LogManager.getLogger();

    private String name;
    private double percentageComplete;
    LocalDate dueDate;

    public Task(String name, double percentageComplete, LocalDate dueDate)
    {
        this.name = name;
        this.percentageComplete = percentageComplete;
        this.dueDate = dueDate;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public LocalDate getDueDate()
    {
        return this.dueDate;
    }

    @Override
    public double getPercentageComplete()
    {
        return this.percentageComplete;
    }

    @Override
    public void print()
    {
        LOG.info("\t"+this.toString());
    }

    @Override
    public String toString()
    {
        return "Task{" +
                "name='" + name + '\'' +
                ", percentageComplete=" + percentageComplete +
                ", dueDate=" + dueDate +
                '}';
    }
}
