package com.sainath.ipldashboard.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
    }

    public TeamNotFoundException(String message) {
        super(message);
    }
}
