package com.oauth.exception;

public class TokenAuthenticationException extends RuntimeException {
    public TokenAuthenticationException(String message) {
        super(message);
    }
}
