package com.cricket.server.exceptions;

public class PlayerDoesNotExistsException extends Exception{
    public PlayerDoesNotExistsException(String message) {
        super(message);
    }
}
