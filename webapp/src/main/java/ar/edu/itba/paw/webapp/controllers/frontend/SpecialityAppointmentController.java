package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.SpecialityService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by agophurmuz on 5/9/16.
 */
@Controller
public class SpecialityAppointmentController extends BaseController{

    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String SPECIALITY_KEY = "speciality";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";
    private static final String PREV_WEEK_KEY = "prevWeek";
    private static final String NEXT_WEEK_KEY = "nextWeek";


    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping("/speciality/{speciality_id}/appointment_slots")
    public ModelAndView list(
            @PathVariable final Integer speciality_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime weekDate) {
        ModelAndView model = new ModelAndView("speciality_appointment");


        Speciality speciality = specialityService.getById(speciality_id);
        if (speciality == null)
            throw new ResourceNotFoundException();

        Patient patient = currentPatient();

        if (weekDate == null)
            weekDate = LocalDateTime.now();

        final LocalDateTime prevWeek = weekDate.minusWeeks(1);
        final LocalDateTime nextWeek = weekDate.plusWeeks(1);
        final LocalDateTime currentWeek = LocalDateTime.now();

        final boolean showPrevWeek = currentWeek.isBefore(prevWeek) || currentWeek.isEqual(prevWeek);

        final List<Appointment> availableAppointmentsSlots = appointmentService
                .getAvailableBySpeciality(speciality, weekDate);


        model.addObject(APPOINTMENTS_KEY, availableAppointmentsSlots);
        model.addObject(SPECIALITY_KEY, speciality);
        model.addObject(LOGGED_PATIENT_KEY, patient);
        model.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());
        model.addObject(PREV_WEEK_KEY, showPrevWeek ? prevWeek : null);
        model.addObject(NEXT_WEEK_KEY, nextWeek);

        return model;
    }
}
