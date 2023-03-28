package com.switchfully.eurder.exceptions;

public class MandatoryFieldException extends RuntimeException {
    public MandatoryFieldException(String type) {
        super("The " + type + " is a mandatory field");
    }
}
