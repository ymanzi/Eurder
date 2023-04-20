package com.switchfully.eurder.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Access Denied!");
    }
}
