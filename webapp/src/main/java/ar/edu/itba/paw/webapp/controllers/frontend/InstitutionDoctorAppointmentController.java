package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.AppointmentSlotService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Controller for the appointment slots of a doctor within an institution
 */
@Controller
public class InstitutionDoctorAppointmentController extends BaseController {

    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String INSTITUTION_KEY = "institution";
    private final static String DOCTOR_KEY = "doctor";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";
    private static final String PREV_WEEK_KEY = "prevWeek";
    private static final String NEXT_WEEK_KEY = "nextWeek";

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentSlotService appointmentSlotService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DoctorService doctorService;



    @RequestMapping("/institutions/{institution_id}/doctors/{doctor_id}/appointment_slots")
    public ModelAndView list(
            @PathVariable final int institution_id,
            @PathVariable final int doctor_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date weekDateParam) {
        ModelAndView model = new ModelAndView("institution_doctor_appointment");
        LocalDateTime weekDate;
        if (weekDateParam == null)
            weekDate = LocalDateTime.now()
                        .with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue())
                        .with(ChronoField.SECOND_OF_DAY, 0);
        else
            weekDate = LocalDateTime.ofInstant(weekDateParam.toInstant(), ZoneId.systemDefault());
        Institution institution = institutionService.get(institution_id);
        if (institution == null)
            throw new ResourceNotFoundException();

        Doctor doctor = doctorService.get(doctor_id);
        if (doctor == null)
            throw new ResourceNotFoundException();

        Patient patient = currentPatient();

        final LocalDateTime prevWeek = weekDate.minusWeeks(1);
        final LocalDateTime nextWeek = weekDate.plusWeeks(1);
        final LocalDateTime currentWeek = LocalDateTime.now();

        final boolean showPrevWeek = currentWeek.isBefore(prevWeek) || currentWeek.isEqual(prevWeek);

        final List<Appointment> availableAppointments = appointmentService
                .getAvailableByDoctorInInstitution(doctor, institution, weekDate);


        model.addObject(APPOINTMENTS_KEY, availableAppointments);
        model.addObject(INSTITUTION_KEY, institution);
        model.addObject(DOCTOR_KEY, doctor);
        model.addObject(LOGGED_PATIENT_KEY, patient);
        model.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());
        model.addObject(PREV_WEEK_KEY, showPrevWeek ? prevWeek : null);
        model.addObject(NEXT_WEEK_KEY, nextWeek);

        return model;
    }

    
}
