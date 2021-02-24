package com.goals.exception;

public class GoalDoesNotExistException extends Exception
{
    private final static String message = "Goal does not exist!";
    public GoalDoesNotExistException()
    {
        super(message);
    }
}
