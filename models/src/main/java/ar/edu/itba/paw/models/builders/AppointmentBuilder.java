package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.errors.InvalidCreationOfPastAppointment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;

public class AppointmentBuilder implements Builder<Appointment> {

    private Patient patient;
    private AppointmentSlot slot;
    private Integer weekNumber;
    private Integer year;
    private String comments;

    public AppointmentBuilder setPatient(final Patient patient) {
        this.patient = patient;
        return this;
    }

    public AppointmentBuilder setSlot(final AppointmentSlot slot) {
        this.slot = slot;
        return this;
    }

    public AppointmentBuilder setWeek(final Integer weekNumber, final Integer year) {
        this.weekNumber = weekNumber;
        this.year = year;
        return this;
    }

    public AppointmentBuilder setComments(final String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public Appointment build() {
        return new Appointment(patient, slot, weekNumber, year, comments);
    }
}
