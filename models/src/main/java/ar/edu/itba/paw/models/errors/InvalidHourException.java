package ar.edu.itba.paw.models.errors;

import sun.plugin.dom.exception.InvalidStateException;

public class InvalidHourException extends InvalidStateException {

    public InvalidHourException() {
        super("La hora debe estar entre 0 y 23");
    }
}
