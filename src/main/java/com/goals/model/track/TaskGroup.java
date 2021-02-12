package com.goals.model.track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Task Group Component contains a list of tasks.
 *
 * @author kemendran.moodley
 */
public class TaskGroup extends TaskComponent
{
    private static final Logger LOG = LogManager.getLogger();

    private String name;
    private double percentageComplete;
    private LocalDate dueDate;
    private List<TaskComponent> taskItems = new ArrayList<>();


    public TaskGroup(String name, double percentageComplete, LocalDate dueDate)
    {
        this.name = name;
        this.percentageComplete = percentageComplete;
        this.dueDate = dueDate;
    }

    @Override
    public void add(TaskComponent taskComponent)
    {
        taskItems.add(taskComponent);
    }

    @Override
    public void remove(TaskComponent taskComponent)
    {
        taskItems.remove(taskComponent);
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
        LOG.info(this.toString());
        taskItems.forEach(t -> t.print());
    }

    @Override
    public String toString()
    {
        return "TaskGroup{" +
                "name='" + name + '\'' +
                ", percentageComplete=" + percentageComplete +
                ", dueDate=" + dueDate +
                ", tasks=" + taskItems.size() +

                '}';
    }
}
