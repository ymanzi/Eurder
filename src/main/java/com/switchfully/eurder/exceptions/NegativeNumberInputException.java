package com.switchfully.eurder.exceptions;

public class NegativeNumberInputException extends RuntimeException {
    public NegativeNumberInputException(String type) {
        super("The " + type + " can not have a negative value!");
    }
}
