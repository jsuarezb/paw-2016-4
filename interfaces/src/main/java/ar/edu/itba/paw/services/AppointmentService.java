package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {

    Appointment create(int patientId, int doctorId, int slotId, Date startDate, String comment);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId);

}
