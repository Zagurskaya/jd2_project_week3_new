package com.gmail.aperavoznikau.jd2w3.data.exception;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
