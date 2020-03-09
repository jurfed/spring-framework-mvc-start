package ru.otus.spring.exceptions;

public class NoSuchUserException extends Exception{
    private static final String msg = "There is no user with id = ";
    public NoSuchUserException(int id) {
        super(msg + id);
    }
}
