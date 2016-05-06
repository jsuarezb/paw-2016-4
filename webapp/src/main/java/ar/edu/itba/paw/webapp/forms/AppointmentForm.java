package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Form object modelling a new appointment request
 */
public class AppointmentForm {

    @NotNull
    private int patientId;

    @NotNull
    private int doctorId;

    @NotNull
    private int slotId;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private DateTime startDate;

    @Length(max = 255)
    private String comment;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
