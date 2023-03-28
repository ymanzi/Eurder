package com.switchfully.eurder.exceptions;

public class CustomerWithThatEmailAlreadyExist extends RuntimeException {
    public CustomerWithThatEmailAlreadyExist() {
        super("An account with that email already Exists!");
    }
}
