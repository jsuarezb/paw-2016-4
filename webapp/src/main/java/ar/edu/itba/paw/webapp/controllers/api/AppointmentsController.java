package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.models.errors.InvalidCreationOfPastAppointment;
import ar.edu.itba.paw.services.*;
import ar.edu.itba.paw.webapp.dto.AppointmentDTO;
import ar.edu.itba.paw.webapp.dto.IdDTO;
import ar.edu.itba.paw.webapp.dto.PagedResultDTO;
import ar.edu.itba.paw.webapp.params.AppointmentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Autowired
    AppointmentSlotService appointmentSlotService;

    @Autowired
    DoctorService doctorService;

    @GET
    public Response listAvailableAppointmentsSlots(@QueryParam("speciality") final Integer specialityId,
                                                   @QueryParam("neighborhood") final String neighborhood,
                                                   @QueryParam("institution") final Integer institutionId,
                                                   @QueryParam("weekOfYear") final Integer weekOfYear,
                                                   @QueryParam("year") final Integer year,
                                                   @QueryParam("doctorId") final Integer doctorId,
                                                   @DefaultValue("0") @QueryParam("page") final int page) {
        final PagedResult<Appointment> pageResult =
                appointmentService.search(weekOfYear, year, institutionId, specialityId, neighborhood, doctorId, page);
        final List<AppointmentDTO> appointments =
                pageResult.getResults().stream().map(AppointmentDTO::new).collect(Collectors.toList());

        final PagedResultDTO<AppointmentDTO> pagedResultDTO = new PagedResultDTO<>(appointments, pageResult.getPage(),
                pageResult.getPageSize(), pageResult.getTotal());

        final GenericEntity<PagedResultDTO<AppointmentDTO>> entity =
                new GenericEntity<PagedResultDTO<AppointmentDTO>>(pagedResultDTO){};

        return Response.ok(entity).build();
    }

    @GET
    @Path("/patient")
    public Response patientAppointments() {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return unauthorized();
        }
        final Patient patient = user.getPatient();

        if (patient == null) {
            return badRequest("Debe ser paciente");
        }
        final List<Appointment> patientAppointments = appointmentService.getByPatient(patient);
        final GenericEntity<List<AppointmentDTO>> list = new GenericEntity<List<AppointmentDTO>>(AppointmentDTO.fromList(patientAppointments)) {};
        return ok(list);
    }

    @GET
    @Path("/doctor")
    public Response doctorAppointments(@QueryParam("future") final Boolean future) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return unauthorized();
        }
        final Doctor doctor = user.getDoctor();
        if (doctor == null) {
            return forbidden();
        }

        final List<Appointment> appointments = future
                ? appointmentService.getIncomingAppointments(doctor)
                : appointmentService.getPastAppointments(doctor);

        final List<AppointmentDTO> appointmentDTOList = AppointmentDTO.fromList(appointments);
        final GenericEntity<List<AppointmentDTO>> entity =
                new GenericEntity<List<AppointmentDTO>>(appointmentDTOList){};

        return ok(entity);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final int id){
        boolean n = appointmentService.cancel(id);
        if(!n)
            return notFound();
        else
            return ok(new IdDTO(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final AppointmentParams params){
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return unauthorized();
        }
        final Patient patient = user.getPatient();
        if (patient == null) {
            return badRequest("El turno no puede ser reservado por un doctor");
        }

        final AppointmentSlot appointmentSlot = appointmentSlotService.getById(params.slotId);
        if(appointmentSlot == null)
            return badRequest("AppointmentSlot does not exist");
        final Doctor doctor = appointmentSlotService.getDoctorInSlot(appointmentSlot.getId());
        final Appointment appointment;
        try {
          appointment = appointmentService.create(patient, doctor, appointmentSlot,
                  params.weekNumber, params.year, params.comment);
        } catch (InvalidCreationOfPastAppointment ex) {
            return badRequest("No se puede reservar un turno en el pasado");
        }

        if(appointment == null)
            return badRequest("El turno ya fue reservado.");

        return ok(new AppointmentDTO(appointment));
    }
}



