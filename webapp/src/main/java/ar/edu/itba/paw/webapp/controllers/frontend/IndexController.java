package ar.edu.itba.paw.webapp.controllers.frontend;


import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import ar.edu.itba.paw.webapp.forms.SearchByDoctorForm;
import ar.edu.itba.paw.webapp.forms.SearchBySpecialityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    private static final String SPECIALITIES_KEY = "specialities";
    private final static String APPOINTMENTS_KEY = "appointments";
    private final static String SPECIALITY_KEY = "speciality";
    private static final String LOGGED_PATIENT_KEY = "user";
    private static final String APPOINTMENT_FORM_MODEL_KEY = "newAppointment";
    private static final String PREV_WEEK_KEY = "prevWeek";
    private static final String NEXT_WEEK_KEY = "nextWeek";

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute SearchBySpecialityForm searchBySpecialityForm,
                              @ModelAttribute SearchByDoctorForm searchByDoctorForm) {
        ModelAndView mav = new ModelAndView("index");

        List<Speciality> specialities = specialityService.getAll();
        mav.addObject(SPECIALITIES_KEY, specialities);

        List<Appointment> appointments = appointmentService.getAll();

        return mav;
    }
    @RequestMapping(path = "/search_by_speciality", method = RequestMethod.GET)
    public ModelAndView searchBySpeciality(@ModelAttribute @Valid SearchBySpecialityForm searchBySpecialityForm,
                                           BindingResult bindingResult) {
        Integer speciality_id = searchBySpecialityForm.getSpecialityId();
        LocalDateTime weekDate = LocalDateTime.now();

        ModelAndView model = new ModelAndView("speciality_appointment");


        Speciality speciality = specialityService.getById(speciality_id);
        if (speciality == null)
            throw new ResourceNotFoundException();

        Patient patient = currentPatient();

        final LocalDateTime prevWeek = weekDate.minus(1, ChronoUnit.WEEKS);
        final LocalDateTime nextWeek = weekDate.plus(1, ChronoUnit.WEEKS);
        final LocalDateTime currentWeek = LocalDateTime.now()
                .with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue())
                .with(ChronoField.SECOND_OF_DAY, 0);

        final boolean showPrevWeek = currentWeek.isBefore(prevWeek) || currentWeek.isEqual(prevWeek);

        final List<Appointment> availableAppointmentsSlots = appointmentService
                .getAvailableBySpeciality(speciality, weekDate);


        model.addObject(APPOINTMENTS_KEY, availableAppointmentsSlots);
        model.addObject(SPECIALITY_KEY, speciality);
        model.addObject(LOGGED_PATIENT_KEY, patient);
        model.addObject(APPOINTMENT_FORM_MODEL_KEY, new AppointmentForm());
        model.addObject(PREV_WEEK_KEY, showPrevWeek ? prevWeek : null);
        model.addObject(NEXT_WEEK_KEY, nextWeek);

        return model;    }

    @RequestMapping(path = "/search_by_doctor", method = RequestMethod.GET)
    public ModelAndView searchByDoctor(@ModelAttribute @Valid SearchByDoctorForm searchByDoctorForm,
                                       BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return null;
        }
        try {
            //patientService.create(patientForm.getName(),..);

        } catch (Exception e) {
            bindingResult.addError(new FieldError("objectName","field","defaultMessage"));
            return null;
        }
        // Process form
        return new ModelAndView();

    }
}
