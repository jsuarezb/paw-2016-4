package ar.edu.itba.paw.models.errors;

public class InvalidDayOfWeekException extends IllegalStateException {
    public InvalidDayOfWeekException() {
        super("El dia de la semana debe estar entre 0 y 6");
    }
}
