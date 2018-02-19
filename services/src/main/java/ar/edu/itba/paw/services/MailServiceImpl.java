package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;
import javax.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yy");

    private static final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter isoDateTimeFmt = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

    @Autowired
    private MailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void sendAppointmentConfirmationToDoctor(final Appointment appointment, final Doctor doctor,
                                                    final Patient patient) {
        final Map<String, Object> context = new HashMap<>();
        final LocalDateTime date = appointment.getDate();
        context.put("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.put("appointmentDate", date.format(dateFmt));
        context.put("appointmentTime", date.format(timeFmt));


        final String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine,
                "templates/doctor_booking.vm",
                "UTF-8",context);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(patient.getUser().getEmail());
                message.setSubject("Cancelaste tu turno");
                message.setFrom("chopidoturnos@gmail.com"); // could be parameterized...


                message.setText(emailText, true);
            }

        };
        try {
            ((JavaMailSender)mailSender).send(preparator);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
        }
    }

    @Override
    public void sendAppointmentConfirmationToPatient(final Appointment appointment,
                                                     final Doctor doctor,
                                                     final Patient patient) {
        final VelocityEngine ve = new VelocityEngine();
        ve.init();

        final LocalDateTime date = appointment.getDate();
        final Institution institution = appointment.getSlot().getWorksIn().getInstitution();
        final Address institutionAddress = institution.getAddress();

        final Map<String, Object> context = new HashMap<>();

        context.put("doctorName", String.format("%s %s", doctor.getUser().getFirstName(), doctor.getUser().getLastName()));
        context.put("appointmentDate", date.format(dateFmt));
        context.put("appointmentTime", date.format(timeFmt));
        context.put("institutionName", institution.getName());
        context.put("institutionStreetNumber", institutionAddress.getStreetNumber());
        context.put("institutionStreetName", institutionAddress.getStreetName());
        context.put("institutionNeighborhood", institutionAddress.getNeighborhood());
        context.put("institutionCity", institutionAddress.getCity());
        context.put("institutionState", institutionAddress.getState());
        context.put("institutionCountry", institutionAddress.getCountry());
        context.put("appointmentStartDatetime", date.format(isoDateTimeFmt));
        context.put("appointmentEndDatetime", date.plusHours(1).format(isoDateTimeFmt));

        try {
            final String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine,
                    "templates/patient_booked_appointment.vm",
                    "UTF-8",
                    context);

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(patient.getUser().getEmail());
                    message.setSubject("¡Reservaste un turno!");
                    message.setFrom("chopidoturnos@gmail.com"); // could be parameterized...


                    message.setText(emailText, true);
                }

            };
            ((JavaMailSender)mailSender).send(preparator);


        } catch (final MailException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendAppointmentCancellationToDoctor(final Appointment appointment,
                                                    final Doctor doctor,
                                                    final Patient patient) {

        final Map<String, Object> context = new HashMap<>();
        final LocalDateTime date = appointment.getDate();
        context.put("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.put("appointmentDate", date.format(dateFmt));
        context.put("appointmentTime", date.format(timeFmt));


        final String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine,
                "templates/doctor_cancellation.vm",
                "UTF-8",context);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(doctor.getUser().getEmail());
                message.setSubject("Cancelaste tu turno");
                message.setFrom("chopidoturnos@gmail.com"); // could be parameterized...


                message.setText(emailText, true);
            }

        };
        try {
            ((JavaMailSender)mailSender).send(preparator);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
        }

    }

    @Override
    public void sendAppointmentCancellationToPatient(final Appointment appointment,
                                                     final Doctor doctor,
                                                     final Patient patient) {
        final Map<String, Object> context = new HashMap<>();
        final LocalDateTime date = appointment.getDate();
        context.put("doctorName", String.format("%s %s", doctor.getUser().getFirstName(), doctor.getUser().getLastName()));
        context.put("appointmentDate", date.format(dateFmt));
        context.put("appointmentTime", date.format(timeFmt));


        final String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine,
                "templates/patient_cancelled_appointment.vm",
                "UTF-8",context);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(patient.getUser().getEmail());
                message.setSubject("Cancelaste tu turno");
                message.setFrom("chopidoturnos@gmail.com"); // could be parameterized...


                message.setText(emailText, true);
            }

        };
        try {
            ((JavaMailSender)mailSender).send(preparator);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
        }
    }

    @Override
    public void sendPasswordRecoveryEmail(final String email, final String token) {

        final Map<String, Object> context = new HashMap<>();
        context.put("token", token);

        final String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine,
                "templates/reset_pass.vm",
                "UTF-8",context);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(email);
                message.setSubject("Restablecer contraseña");
                message.setFrom("chopidoturnos@gmail.com"); // could be parameterized...


                message.setText(emailText, true);
            }

        };
        try {
            ((JavaMailSender)mailSender).send(preparator);
        } catch (MailException ex) {
            LOG.error("Mail not sent.", ex);
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

    @Bean
    private static VelocityEngineFactoryBean velocityEngineFactoryBean() {
        final VelocityEngineFactoryBean engineFactory = new VelocityEngineFactoryBean();
        final Map<String, Object> velocityProperties = new HashMap<>();

        velocityProperties.put("resource.loader", "class");
        velocityProperties.put("class.resource.loader.class", ClasspathResourceLoader.class.getCanonicalName());

        engineFactory.setVelocityPropertiesMap(velocityProperties);

        return engineFactory;
    }
}
