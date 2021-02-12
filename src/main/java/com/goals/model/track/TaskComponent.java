package com.goals.model.track;

import java.time.LocalDate;

public abstract class TaskComponent
{
    public void add(TaskComponent taskComponent)
    {
        throw new UnsupportedOperationException("Cannot add item to catalog.");
    }

    public void remove(TaskComponent taskComponent)
    {
        throw new UnsupportedOperationException("Cannot remove item from catalog.");
    }

    public String getName()
    {
        throw new UnsupportedOperationException("Cannot return name.");
    }

    public LocalDate getDueDate()
    {
        throw new UnsupportedOperationException("Cannot return due date.");
    }

    public double getPercentageComplete()
    {
        throw new UnsupportedOperationException("Cannot return percentage complete.");
    }

    public void print()
    {
        throw new UnsupportedOperationException("Cannot print.");
    }
}
