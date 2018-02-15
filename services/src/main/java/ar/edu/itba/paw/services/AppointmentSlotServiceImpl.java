package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;
import ar.edu.itba.paw.models.WorksIn;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    @Autowired
    private AppointmentSlotDao appointmentSlotDao;

    public AppointmentSlot create(final WorksIn worksIn,
                                  final int dayOfWeek,
                                  final int startHour) {
        return appointmentSlotDao.create(worksIn, dayOfWeek, startHour);
    }

    public AppointmentSlot getById(final int id) {
        return appointmentSlotDao.getById(id);
    }

    public List<AppointmentSlot> getByDoctor(final Doctor doctor) {
        return appointmentSlotDao.getByDoctor(doctor);
    }

    public List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor, final Integer weekNumber, final Integer year) {
        return appointmentSlotDao.getAvailableByDoctor(doctor, weekNumber, year);
    }

    public PagedResult<AppointmentSlot> search(final Integer weekNumber, final Integer year,
                                               final Integer institution_id, final Integer speciality_id,
                                               final String neighborhood, final Integer doctor_id,
                                               final int page) {
        return appointmentSlotDao
                .search(weekNumber, year, institution_id, speciality_id, neighborhood, doctor_id, page);
    }

    /* package */ void setAppointmentSlotDao(AppointmentSlotDao appointmentSlotDao) {
        this.appointmentSlotDao = appointmentSlotDao;
    }

    public Doctor getDoctorInSlot(final int slotId){
        return appointmentSlotDao.getDoctorInSlot(slotId);
    }
}
