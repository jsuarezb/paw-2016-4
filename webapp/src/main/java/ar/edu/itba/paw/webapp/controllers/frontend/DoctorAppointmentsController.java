package ar.edu.itba.paw.webapp.controllers.frontend;


import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/doctors/{doctor_id}")
public class DoctorAppointmentsController extends BaseController {

    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String DOCTOR_KEY = "doctor";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";
    private static final String PREV_WEEK_KEY = "prevWeek";
    private static final String NEXT_WEEK_KEY = "nextWeek";

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    private final Comparator<Appointment> appointmentComparator = new Comparator<Appointment>() {
        @Override
        public int compare(Appointment o1, Appointment o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };


    @RequestMapping(value = "/appointment_slots", method = RequestMethod.GET)
    public ModelAndView list(
            @PathVariable final Integer doctor_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date weekDateParam) {
        ModelAndView mav = new ModelAndView("doctor_appointments");
        LocalDateTime weekDate = LocalDateTime.ofInstant(weekDateParam.toInstant(), ZoneId.systemDefault());
        final Doctor doctor = doctorService.get(doctor_id);
        if (doctor == null)
            throw new ResourceNotFoundException();

        if (weekDate == null)
            weekDate = LocalDateTime.now();

        weekDate = weekDate
                .with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue())
                .with(ChronoField.SECOND_OF_DAY, 0);

        final LocalDateTime prevWeek = weekDate.minusWeeks(1);
        final LocalDateTime nextWeek = weekDate.plusWeeks(1);
        final LocalDateTime currentWeek = LocalDateTime.now()
                .with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue())
                .with(ChronoField.SECOND_OF_DAY, 0);

        final boolean showPrevWeek = currentWeek.isBefore(prevWeek) || currentWeek.isEqual(prevWeek);

        final List<Appointment> appointmentList = Collections.emptyList();
        //TODO appointmentService.getAvailableByDoctor(doctor, weekDate);
        Collections.sort(appointmentList, appointmentComparator);

        final Patient patient = currentPatient();

        mav.addObject(DOCTOR_KEY, doctor);
        mav.addObject(APPOINTMENTS_KEY, appointmentList);
        mav.addObject(PREV_WEEK_KEY, showPrevWeek ? prevWeek : null);
        mav.addObject(NEXT_WEEK_KEY, nextWeek);
        mav.addObject(LOGGED_PATIENT_KEY, patient);
        mav.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());

        return mav;
    }
}
