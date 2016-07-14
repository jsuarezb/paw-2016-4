package ar.edu.itba.paw.webapp.controllers;

/**
 * Created by me on 5/5/16.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        this("Bad request.");
    }
    public BadRequestException(final String message) {
        super(message);
    }
}
