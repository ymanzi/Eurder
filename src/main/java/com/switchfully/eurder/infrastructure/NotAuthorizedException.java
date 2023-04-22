package com.switchfully.eurder.infrastructure;

public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException(String additionalContext) {
        super(additionalContext);
    }
}
