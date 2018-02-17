package ar.edu.itba.paw.models.errors;

public class InvalidCreationOfPastAppointment extends IllegalStateException {
    public static final long serialVersionUID = 1L;
    public InvalidCreationOfPastAppointment() {
        super();
    }

    public InvalidCreationOfPastAppointment(final String message) {
        super("Creacion no válida de un turno en el pasado");
    }
}
