package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.AppointmentSlotService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.controllers.BadRequestException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for appointments
 */
@Controller
@RequestMapping(value = "/appointments")
public class AppointmentController extends BaseController {

    private static final String APPOINTMENT_KEY = "appointment";
    private static final String APPOINTMENTS_KEY = "appointments";
    private static final String DOCTOR_KEY = "doctor";

    @Autowired
    AppointmentService as;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentSlotService appointmentSlotService;

    @RequestMapping(method = { RequestMethod.POST })
    public ModelAndView create(@Valid @ModelAttribute("newAppointment") final AppointmentForm appointmentForm,
                               final BindingResult errors,
                               final HttpSession session) {
        if (errors.hasErrors())
            throw new BadRequestException();

        final Patient patient = patientService.get(currentPatient().getId());
        final Doctor doctor = doctorService.get(appointmentForm.getDoctorId());
        final AppointmentSlot appointmentSlot = appointmentSlotService.getById(appointmentForm.getSlotId());

        final Appointment appointment = as.create(patient, doctor, appointmentSlot,
                appointmentForm.getStartDate(), appointmentForm.getComment());

        if (appointment == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);

        final ModelAndView mav = new ModelAndView("appointment_registered");
        mav.addObject(APPOINTMENT_KEY, appointment);

        return mav;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        if (id == null)
            throw new ResourceNotFoundException();

        int n = as.cancel(id);
        if (n <= 0)
            throw new ResourceNotFoundException();
    }

    @RequestMapping(method = { RequestMethod.GET })
    public ModelAndView list() {
        final ModelAndView mav = new ModelAndView("patient_appointments");

        Patient patient = currentPatient();
        List<Appointment> patientAppointments = as.getByPatient(patient);

        mav.addObject(APPOINTMENTS_KEY, patientAppointments);

        return mav;
    }

}
