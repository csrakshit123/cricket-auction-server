package com.cricket.server.exceptions;

public class PlayerDoesNotExistsInPoolException extends Exception{
    public PlayerDoesNotExistsInPoolException(String message) {
        super(message);
    }
}
