package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.persistence.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao dao;

    public Appointment create(int patientId, int doctorId, int slotId, Date startDate, String comment) {
        return dao.create(patientId, doctorId, slotId, startDate, comment);
    }

    public List<Appointment> getByDoctor(int doctorId) {
        return dao.getByDoctor(doctorId);
    }

    public List<Appointment> getByPatient(int patientId) {
        return dao.getByPatient(patientId);
    }
}
