package com.quartz.quartz.exception;

public class QuartzException extends Exception {
    public QuartzException(String errorMessage, Object... params) {

        super(String.format(errorMessage, params));
    }

    public QuartzException(String errorMessage) {

        super(errorMessage);
    }
}
