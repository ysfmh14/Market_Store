package com.example.market_store.exceptions;

public class EntityAlreadyExisteException extends RuntimeException {
    public EntityAlreadyExisteException( String message) {
        super(message);
    }
}
