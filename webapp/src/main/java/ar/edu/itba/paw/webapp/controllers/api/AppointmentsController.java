package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.auth.LoggedUserFinder;
import ar.edu.itba.paw.webapp.dto.AppointmentDTO;
import ar.edu.itba.paw.webapp.dto.AppointmentSlotDTO;
import ar.edu.itba.paw.webapp.filters.StatelessAuthenticationFilter;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public Response listAvailableAppointmentsSlots(@QueryParam("speciality") Integer speciality_id,
                                                   @QueryParam("neighborhood") String neighborhood,
                                                   @QueryParam("institution") Integer institution_id,
                                                   @QueryParam("firstName") String firstName,
                                                   @QueryParam("lastName") String lastName,
                                                   @DefaultValue("0") @QueryParam("page") int page) {
        final List<Appointment> appointments =
                appointmentService.search(institution_id, neighborhood, speciality_id, firstName, lastName, page);
        final GenericEntity<List<AppointmentDTO>> list =
                new GenericEntity<List<AppointmentDTO>>(AppointmentDTO.fromList(appointments)){};

        return Response.ok(list).build();
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

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final int id){
        boolean n = appointmentService.cancel(id);
        if(!n)
            return notFound();
        else
            return ok(id);
    }
}
