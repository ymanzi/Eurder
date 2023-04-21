package com.switchfully.eurder.infrastructure.exceptions;

public class MandatoryFieldException extends RuntimeException {
    public MandatoryFieldException(String type) {
        super("The " + type + " is a mandatory field");
    }
}
