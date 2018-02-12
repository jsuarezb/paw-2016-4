package ar.edu.itba.paw.models.errors;

public class InvalidCreationOfPastAppointment extends IllegalStateException {
    public InvalidCreationOfPastAppointment() {
        super();
    }

    public InvalidCreationOfPastAppointment(String message) {
        super("Creacion no válida de un turno en el pasado");
    }
}
