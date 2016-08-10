package com.jamesfigler.kata.babysitter;

public class TimeNotAllowedException extends Exception {

    public TimeNotAllowedException(String message) {
        super(message);
    }
}
