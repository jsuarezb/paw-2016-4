package ar.edu.itba.paw.models.errors;

public class InvalidHourException extends IllegalStateException {
    public static final long serialVersionUID = 1L;

    public InvalidHourException() {
        super("La hora debe estar entre 0 y 23");
    }
}
