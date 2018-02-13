package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.errors.InvalidCreationOfPastAppointment;

import java.time.LocalDateTime;

public class AppointmentBuilder implements Builder<Appointment> {

    private Patient patient;
    private AppointmentSlot slot;
    private LocalDateTime date;
    private String comments;

    public AppointmentBuilder setPatient(final Patient patient) {
        this.patient = patient;
        return this;
    }

    public AppointmentBuilder setSlot(final AppointmentSlot slot) {
        this.slot = slot;
        return this;
    }

    public AppointmentBuilder setDate(final LocalDateTime date) {
        this.date = date;
        return this;
    }

    public AppointmentBuilder setComments(final String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public Appointment build() {
        if (this.date.isBefore(LocalDateTime.now()))
            throw new InvalidCreationOfPastAppointment();

        return new Appointment(patient, slot, date, comments);
    }
}
