package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by santi698 on 23/03/16.
 */

@Controller
public class InstitutionDoctorAppointmentController {

    @RequestMapping("/institutions/{institution_id}/doctors/{doctor_id}/appointment_slots")
    public Object list(@PathVariable final int institution_id) throws MethodNotAllowedException {
        ModelAndView model = new ModelAndView("institution_doctor_appointment");
        List<Appointment> appointments = new ArrayList<>();

        Doctor doctor = new Doctor(1, "Doctor name", "Doctor last name", "Speciality", "Email", "password");
        Address address = new Address("Street Name", 9999, "Apartment", "City", "State", "Country");
        Institution institution = new Institution(institution_id, "Institution X", address);

        for (int i = 0; i < 10; i++) {
            AppointmentSlot slot = new AppointmentSlot(DayOfWeek.of(i % 7 + 1), 20, institution);
            Appointment appointment = new Appointment(slot, Date.from(Instant.now()), "Comment");
            appointments.add(appointment);
        }

        model.addObject("appointments", appointments);
        model.addObject("institution", institution);
        model.addObject("doctor", doctor);

        return model;
    }
    
}
