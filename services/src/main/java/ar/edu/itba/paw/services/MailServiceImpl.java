package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

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
            LOG.error("Mail not sent.", ex);
        }
    }

    @Override
    public void sendAppointmentConfirmationToPatient(final Appointment appointment, final Doctor doctor,
                                                     final Patient patient) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("chopidoturnos@gamil.com");
        msg.setSubject("Turno reservado.");
        msg.setTo("chopidoturnos@gamil.com");
        msg.setText("Usted reservó un turno para el día " + appointment.getDate().format(dateFmt)
                + " a las " + appointment.getDate().format(timeFmt) + " hs"
                + String.format("%s, %s", doctor.getLastName(), doctor.getName())
        );

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            // TODO log error
        }
    }

    @Bean
    private static MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

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
