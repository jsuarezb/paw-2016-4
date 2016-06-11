package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;

public interface MailService {

    void sendAppointmentConfirmationToDoctor(final Appointment appointment, final Doctor doctor, final Patient patient);

    void sendAppointmentConfirmationToPatient(final Appointment appointment, final Doctor doctor, final Patient patient);

}
