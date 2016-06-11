package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class MailServiceImpl implements MailService {

    private static final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendAppointmentConfirmationToDoctor(final Appointment appointment, final Doctor doctor,
                                                    final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("no-reply@chopidoturnos.com");
        msg.setSubject("Turno reservado.");
        msg.setTo(doctor.getEmail());
        msg.setText("El paciente " + String.format("%s, %s", patient.getLastName(), patient.getName())
                + " reservó un turno para el día " + appointment.getDate().format(dateFmt)
                + " a las " + appointment.getDate().format(timeFmt) + " hs"
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            // TODO log error
        }
    }

    @Override
    public void sendAppointmentConfirmationToPatient(final Appointment appointment, final Doctor doctor,
                                                     final Patient patient) {
        // TODO
    }

    @Bean
    private static MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setProtocol("smtp");
        mailSender.setHost("localhost");
        mailSender.setPort(25);

        return mailSender;
    }
}
