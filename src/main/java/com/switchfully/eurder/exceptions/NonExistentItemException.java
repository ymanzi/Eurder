package com.switchfully.eurder.exceptions;

public class NonExistentItemException extends RuntimeException {
    public NonExistentItemException(String type) {
        super("Non existent " + type + "!");
    }
}
