package com.switchfully.eurder.infrastructure;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String additionalContext, Class classOfEntity, Integer id) {
        super("During " + additionalContext + ", the following entity was not found: "
                + classOfEntity.getSimpleName() + " with id = " + id);
    }
}
