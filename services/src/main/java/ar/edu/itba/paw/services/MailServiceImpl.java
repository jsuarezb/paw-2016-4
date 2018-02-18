package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendAppointmentConfirmationToDoctor(final Appointment appointment, final Doctor doctor,
                                                    final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        final LocalDateTime date = appointment.getDate();
        msg.setFrom("no-reply@chopidoturnos.com");
        msg.setSubject("Turno reservado.");
        msg.setTo(doctor.getUser().getEmail());
        msg.setText("El paciente " + String.format("%s, %s", patient.getUser().getLastName(), patient.getUser().getFirstName())
                + " reservó un turno para el día " + date.format(dateFmt)
                + " a las " + date.format(timeFmt) + " hs"
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
        }
    }

    @Override
    public void sendAppointmentConfirmationToPatient(final Appointment appointment,
                                                     final Doctor doctor,
                                                     final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        final LocalDateTime date = appointment.getDate();
        msg.setFrom("chopidoturnos@gamil.com");
        msg.setSubject("Turno reservado.");
        msg.setTo(patient.getUser().getEmail());
        msg.setText("Usted reservó un turno para el día " + date.format(dateFmt)
                + " a las " + date.format(timeFmt) + " hs"
                + String.format("%s, %s", doctor.getUser().getLastName(), doctor.getUser().getFirstName())
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            // TODO log error
        }
    }

    @Override
    public void sendAppointmentCancellationToDoctor(final Appointment appointment,
                                                    final Doctor doctor,
                                                    final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        final LocalDateTime date = appointment.getDate();
        msg.setFrom("no-reply@chopidoturnos.com");
        msg.setSubject("Turno cancelado.");
        msg.setTo(doctor.getUser().getEmail());
        msg.setText("El paciente " + String.format("%s, %s", patient.getUser().getLastName(), patient.getUser().getFirstName())
                + " ha cancelado el turno para el día " + date.format(dateFmt)
                + " a las " + date.format(timeFmt) + " hs"
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
        }
    }

    @Override
    public void sendAppointmentCancellationToPatient(final Appointment appointment,
                                                     final Doctor doctor,
                                                     final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        final LocalDateTime date = appointment.getDate();
        msg.setFrom("chopidoturnos@gamil.com");
        msg.setSubject("Turno cancelado.");
        msg.setTo(patient.getUser().getEmail());
        msg.setText("Usted canceló el turno del día " + date.format(dateFmt)
                + " a las " + date.format(timeFmt) + " hs"
                + String.format("%s, %s", doctor.getUser().getLastName(), doctor.getUser().getFirstName())
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            // TODO log error
        }
    }

    @Bean
    private static MailSender mailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setProtocol("smtp");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("chopidoturnos@gmail.com");
        mailSender.setPassword("chopidoturnospaw2016");

        final Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}
