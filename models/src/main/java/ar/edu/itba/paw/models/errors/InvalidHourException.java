package ar.edu.itba.paw.models.errors;

public class InvalidHourException extends IllegalStateException {

    public InvalidHourException() {
        super("La hora debe estar entre 0 y 23");
    }
}
