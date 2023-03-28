package com.switchfully.eurder.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerWithThatEmailAlreadyExist.class)
    protected void customerWithThatEmailAlreadyExist(CustomerWithThatEmailAlreadyExist ex,
                                        HttpServletResponse response) throws IOException {
        logger.error("Exception raised: ", ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerWithThatEmailAlreadyExist.class)
    protected void mandatoryException(MandatoryFieldException ex,
                                      HttpServletResponse response) throws IOException {
        logger.error("Exception raised: ", ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
