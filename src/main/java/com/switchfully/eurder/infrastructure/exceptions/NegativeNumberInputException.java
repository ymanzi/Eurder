package com.switchfully.eurder.infrastructure.exceptions;

public class NegativeNumberInputException extends RuntimeException {
    public NegativeNumberInputException(String type) {
        super("The " + type + " can not have a negative value!");
    }
}
