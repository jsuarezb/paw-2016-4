package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentsSlotsList;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by agophurmuz on 7/17/16.
 */

@Path("api/v1/appointments")
@Component
public class AppointmentsController extends ApiController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private PatientService patientService;

    @GET
    public Response listAvailableAppointmentsSlots(@QueryParam("speciality") int id,
                                                   @QueryParam("neighborhood") String neighborhood,
                                                   @QueryParam("week-date") String day) {
        final List<Appointment> availableAppointmentsSlotsList = appointmentService
                .getAvailableBySpecialityAndNeighborhood(specialityService.getById(id), neighborhood, LocalDateTime.parse(day));
        return Response.ok(new AppointmentsSlotsList(availableAppointmentsSlotsList)).build();
    }

    @GET
    @Path("/patient/{id}")
    public Response patientAponitments(@PathParam("id") final int id) {
        final Patient patient = patientService.get(id);
        if (patient != null) {
            final List<Appointment> patientAppointments = appointmentService.getByPatient(patient);
            System.out.println(patientAppointments);
            GenericEntity<List<AppointmentDTO>> list = new GenericEntity<List<AppointmentDTO>>(AppointmentDTO.fromList(patientAppointments)){

            };
            return ok(list);
        }
        else{
            return notFound();
        }
    }
}
