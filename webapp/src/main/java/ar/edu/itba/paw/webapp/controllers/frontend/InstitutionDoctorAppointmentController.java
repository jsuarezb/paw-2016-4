package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.services.AppointmentSlotService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.*;

/**
 * Controller for the appointment slots of a doctor within an institution
 */
@Controller
public class InstitutionDoctorAppointmentController {

    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String INSTITUTION_KEY = "institution";
    private final static String DOCTOR_KEY = "doctor";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";

    @Autowired
    private AppointmentSlotService appointmentSlotService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DoctorService doctorService;

    private final Comparator<Appointment> appointmentComparator = new Comparator<Appointment>() {
        @Override
        public int compare(Appointment o1, Appointment o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    @RequestMapping("/institutions/{institution_id}/doctors/{doctor_id}/appointment_slots")
    public ModelAndView list(
            @PathVariable final int institution_id,
            @PathVariable final int doctor_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
                throws MethodNotAllowedException {
        ModelAndView model = new ModelAndView("institution_doctor_appointment");

        Institution institution = institutionService.get(institution_id);
        if (institution == null)
            throw new ResourceNotFoundException();

        Doctor doctor = doctorService.get(institution_id);
        if (doctor == null)
            throw new ResourceNotFoundException();

        // TODO: get current patient from the current session
        Patient patient = new Patient(1, "Juan", "Pérez", "jperez@gmail.com", "wasd123");

        DateTime jDate;
        if (date == null) {
            jDate = new DateTime(); // If no date is given, current week will be shown
        } else {
            jDate = new DateTime(date);
        }
        jDate = jDate.withDayOfWeek(DateTimeConstants.MONDAY)
                .withTime(0, 0, 0, 0);

        List<AppointmentSlot> slots = appointmentSlotService
                .getAvailableByDoctor(doctor_id, new Date(jDate.getMillis()));

        List<Appointment> availableAppointments = new ArrayList<>();
        for (AppointmentSlot slot : slots) {
            DateTime appoitmentDate = jDate
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek().getValue());

            Appointment appointment = new Appointment(0, patient.getId(), doctor.getId(), slot,
                    new Date(appoitmentDate.getMillis()), null);

            availableAppointments.add(appointment);
        }

        Collections.sort(availableAppointments, appointmentComparator);

        model.addObject(APPOINTMENTS_KEY, availableAppointments);
        model.addObject(INSTITUTION_KEY, institution);
        model.addObject(DOCTOR_KEY, doctor);
        model.addObject(LOGGED_PATIENT_KEY, patient);
        model.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());

        return model;
    }

    
}
