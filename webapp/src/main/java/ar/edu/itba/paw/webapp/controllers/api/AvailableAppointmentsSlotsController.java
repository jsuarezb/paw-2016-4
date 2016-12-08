package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentsSlotsList;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by agophurmuz on 7/17/16.
 */

@Path("api/v1/available_appointments_slots")
@Component
public class AvailableAppointmentsSlotsController extends ApiController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialityService specialityService;

    @GET
    public Response listAvailableAppointmentsSlots(@QueryParam("speciality") int id,
                                                   @QueryParam("neighborhood") String neighborhood,
                                                   @QueryParam("week-date") String day) {
        final List<Appointment> availableAppointmentsSlotsList = appointmentService
                .getAvailableBySpecialityAndNeighborhood(specialityService.getById(id), neighborhood, LocalDateTime.parse(day));
        return Response.ok(new AppointmentsSlotsList(availableAppointmentsSlotsList)).build();
    }
}
