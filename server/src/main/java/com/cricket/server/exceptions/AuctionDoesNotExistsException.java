package com.cricket.server.exceptions;

public class AuctionDoesNotExistsException extends Exception{
    public AuctionDoesNotExistsException(String message) {
        super(message);
    }
}
