package ar.edu.itba.paw.webapp.controllers;

/**
 * Created by me on 5/5/16.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad request.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
