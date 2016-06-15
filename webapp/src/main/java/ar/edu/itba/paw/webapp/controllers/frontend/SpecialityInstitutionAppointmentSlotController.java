package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.services.SpecialityService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by agophurmuz on 5/8/16.
 */

@Controller
public class SpecialityInstitutionAppointmentSlotController extends BaseController{

    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String INSTITUTION_KEY = "institution";
    private final static String SPECIALITY_KEY = "speciality";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";
    private static final String PREV_WEEK_KEY = "prevWeek";
    private static final String NEXT_WEEK_KEY = "nextWeek";


    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping("/institutions/{institution_id}/specialities/{speciality_id}/appointment_slots")
    public ModelAndView list(
            @PathVariable final Integer institution_id,
            @PathVariable final Integer speciality_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date weekDateParam) {
        ModelAndView model = new ModelAndView("institution_speciality_appointmentslot");

        LocalDateTime weekDate;
        if (weekDateParam == null)
            weekDate = LocalDateTime.now();
        else
            weekDate = LocalDateTime.ofInstant(weekDateParam.toInstant(), ZoneId.systemDefault());

        Institution institution = institutionService.get(institution_id);
        if (institution == null)
            throw new ResourceNotFoundException();

        Speciality speciality = specialityService.getById(speciality_id);
        if (speciality == null)
            throw new ResourceNotFoundException();

        Patient patient = currentPatient();

        if (weekDate == null)
            weekDate = LocalDateTime.now();

        weekDate = weekDate
                .with(DayOfWeek.MONDAY)
                .with(ChronoField.SECOND_OF_DAY, 0);

        final LocalDateTime prevWeek = weekDate.minusWeeks(1);
        final LocalDateTime nextWeek = weekDate.plusWeeks(1);
        final LocalDateTime currentWeek = LocalDateTime.now()
                .with(DayOfWeek.MONDAY)
                .with(ChronoField.SECOND_OF_DAY, 0);

        final boolean showPrevWeek = currentWeek.isBefore(prevWeek) || currentWeek.isEqual(prevWeek);

        final List<Appointment> availableAppointmentsSlots = appointmentService
                .getAvailableBySpecialityInInstitution(speciality, institution, weekDate);


        model.addObject(APPOINTMENTS_KEY, availableAppointmentsSlots);
        model.addObject(INSTITUTION_KEY, institution);
        model.addObject(SPECIALITY_KEY, speciality);
        model.addObject(LOGGED_PATIENT_KEY, patient);
        model.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());
        model.addObject(PREV_WEEK_KEY, showPrevWeek ? prevWeek : null);
        model.addObject(NEXT_WEEK_KEY, nextWeek);

        return model;
    }
}
