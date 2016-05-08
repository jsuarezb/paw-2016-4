package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.webapp.controllers.BadRequestException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * Controller for appointments
 */
@Controller
@RequestMapping(value = "/appointments")
public class AppointmentController {

    private static final String APPOINTMENT_KEY = "appointment";
    private static final String APPOINTMENTS_KEY = "appointments";

    @Autowired
    AppointmentService as;

    @RequestMapping(method = { RequestMethod.POST })
    public ModelAndView create(@Valid @ModelAttribute("newAppointment") final AppointmentForm appointmentForm,
                               final BindingResult errors,
                               final HttpSession session) {
        if (errors.hasErrors())
            throw new BadRequestException();

        final Appointment appointment = as.create(appointmentForm.getPatientId(), appointmentForm.getDoctorId(),
                appointmentForm.getSlotId(), appointmentForm.getStartDate(), appointmentForm.getComment());

        if (appointment == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);

        final ModelAndView mav = new ModelAndView("appointment_registered");
        mav.addObject(APPOINTMENT_KEY, appointment);

        return mav;
    }

    @RequestMapping(method = { RequestMethod.GET })
    public ModelAndView list() {
        final ModelAndView mav = new ModelAndView("patient_appointments");

        // TODO: get patient from session
        Patient patient = new Patient(1, "Juan", "Pérez", "jperez@gmail.com", "wasd123");
        List<Appointment> patientAppointments = as.getByPatient(patient.getId());

        mav.addObject(APPOINTMENTS_KEY, patientAppointments);

        return mav;
    }

}
