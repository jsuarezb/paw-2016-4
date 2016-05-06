package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {

    Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comment);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId);

}
