package com.cricket.server.exceptions;

public class InvalidExistingUserRequest extends Exception{
    public InvalidExistingUserRequest(String message) {
        super(message);
    }
}
