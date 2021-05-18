package com.oauth.exception;

public class TokenCreationException extends RuntimeException {
    public TokenCreationException(String message) {
        super(message);
    }
}
