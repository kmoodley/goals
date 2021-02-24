package com.goals.exception;

public class TaskDoesNotExistException extends Exception
{
    private final static String message = "Task does not exist!";
    public TaskDoesNotExistException()
    {
        super(message);
    }
}
