package com.example.market_store.exception;

public class EntityAlreadyExisteException extends RuntimeException {
    public EntityAlreadyExisteException( String message) {
        super(message);
    }
}
