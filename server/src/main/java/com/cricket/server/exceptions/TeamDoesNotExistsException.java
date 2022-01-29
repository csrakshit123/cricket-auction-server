package com.cricket.server.exceptions;

public class TeamDoesNotExistsException extends Exception{
    public TeamDoesNotExistsException(String message) {
        super(message);
    }
}
