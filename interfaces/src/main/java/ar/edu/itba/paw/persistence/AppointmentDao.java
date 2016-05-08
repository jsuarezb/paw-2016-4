package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao {

    Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comments);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId);

}
