package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao {

    Appointment create(int patientId, int doctorId, int slotId, Date startDate, String comments);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId);

}
