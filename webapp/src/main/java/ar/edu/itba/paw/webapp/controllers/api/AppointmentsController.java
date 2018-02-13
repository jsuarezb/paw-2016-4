package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.services.*;
import ar.edu.itba.paw.webapp.auth.LoggedUserFinder;
import ar.edu.itba.paw.webapp.auth.Token;
import ar.edu.itba.paw.webapp.dto.AppointmentDTO;
import ar.edu.itba.paw.webapp.filters.StatelessAuthenticationFilter;
import ar.edu.itba.paw.webapp.forms.AppointmentForm;
import ar.edu.itba.paw.webapp.params.AppointmentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
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

    @Autowired
    AppointmentSlotService appointmentSlotService;

    @Autowired
    DoctorService doctorService;

    @GET
    public Response listAvailableAppointmentsSlots(@QueryParam("speciality") int id,
                                                   @QueryParam("neighborhood") String neighborhood,
                                                   @QueryParam("week-number") Integer weekNumber,
                                                   @QueryParam("year") Integer year){
        final List<Appointment> availableAppointmentsSlotsList = appointmentService
                .getAvailableBySpecialityAndNeighborhood(specialityService.getById(id), neighborhood, weekNumber, year);
        return Response.ok(new AppointmentsSlotsList(availableAppointmentsSlotsList)).build();
    }

    @GET
    @Path("/patient")//{id}")
    public Response patientAponitments() {//@PathParam("id") final int id) {
        final Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final AppointmentParams params){

        final Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Doctor doctor = doctorService.get(params.doctorId);
        final AppointmentSlot appointmentSlot = appointmentSlotService.getById(params.slotId);
        if(appointmentSlot == null)
            return badRequest("AppointmentSlot does not exist");

        final Appointment appointment = appointmentService.create(patient, doctor, appointmentSlot,
                params.weekNumber, params.year, params.comment);
        if(appointment == null)
            return badRequest("");
        return ok(appointment);
    }
}
