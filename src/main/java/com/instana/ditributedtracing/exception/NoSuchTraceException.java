package com.instana.ditributedtracing.exception;

public class NoSuchTraceException extends Exception {

    public NoSuchTraceException(String message) {
        super(message);
    }
}
