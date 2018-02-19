package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.RatingService;
import ar.edu.itba.paw.webapp.dto.*;
import ar.edu.itba.paw.webapp.params.RatingParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1/doctors")
@Component
public class DoctorsController extends ApiController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RatingService ratingService;

    @GET
    public Response index(@QueryParam("first_name") final String firstName,
                          @QueryParam("last_name") final String lastName,
                          @QueryParam("speciality_id") final Integer speciality_id,
                          @DefaultValue("0") @QueryParam("page") final Integer page) {
        PagedResult<Doctor> doctorsPage;

        if (speciality_id != null) {
            doctorsPage = doctorService.searchBySpeciality(speciality_id, page);
        } else {
            if (firstName == null && lastName == null) {
                doctorsPage = doctorService.getAll(page);
            } else {
                doctorsPage = doctorService.searchByName(firstName, lastName, page);
            }
        }

        final List<DoctorDTO> doctorDTOs = DoctorDTO.fromList(doctorsPage.getResults());
        final PagedResultDTO<DoctorDTO> pagedResult =
                new PagedResultDTO<>(doctorDTOs, doctorsPage.getPage(), doctorsPage.getPageSize(),
                        doctorsPage.getTotal());

        final GenericEntity<PagedResultDTO<DoctorDTO>> list =
                new GenericEntity<PagedResultDTO<DoctorDTO>>(pagedResult){};

        return ok(list);
    }

    @GET
    @Path("/available")
    public Response availableDoctors(@QueryParam("speciality") final Integer specialityId,
                                     @QueryParam("neighborhood") final String neighborhood,
                                     @QueryParam("institution") final Integer institutionId,
                                     @QueryParam("weekOfYear") final Integer weekOfYear,
                                     @QueryParam("year") final Integer year,
                                     @DefaultValue("0") @QueryParam("page") final int page) {
        final PagedResult<Doctor> doctorsPage = doctorService
                .getAvailable(specialityId, neighborhood, institutionId, weekOfYear, year, page);

        final List<DoctorDTO> doctorsDTOs = doctorsPage.getResults().stream()
                .map(DoctorDTO::new)
                .collect(Collectors.toList());

        final PagedResultDTO<DoctorDTO> doctorsDTOPage =
                new PagedResultDTO<>(doctorsDTOs, doctorsPage.getPage(), doctorsPage.getPageSize(), doctorsPage.getTotal());

        final GenericEntity<PagedResultDTO<DoctorDTO>> entity =
                new GenericEntity<PagedResultDTO<DoctorDTO>>(doctorsDTOPage){};

        return ok(entity);
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") final Integer id) {
        final Doctor doctor = doctorService.get(id);

        if (doctor != null) {
            return ok(new DoctorDTO(doctor));
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/appointments")
    public Response doctorAppointments(@PathParam("id") final Integer id,
                                       @QueryParam("weekNumber") Integer weekNumber,
                                       @QueryParam("year") Integer year) {
        final Doctor doctor = doctorService.get(id);
        if (doctor == null)
            return notFound();

        if (year == null)
            year = LocalDateTime.now().getYear();

        if (weekNumber == null)
            weekNumber = LocalDateTime.now().get(WeekFields.of(DayOfWeek.SUNDAY, 4).weekOfYear());

        final List<Appointment> appointments = appointmentService.getAvailableByDoctor(doctor, weekNumber, year);
        final GenericEntity<List<AppointmentDTO>> entity =
                new GenericEntity<List<AppointmentDTO>>(AppointmentDTO.fromList(appointments)){};

        return ok(entity);
    }

    @GET
    @Path("/{id}/rating_summary")
    public Response ratingSummary(@PathParam("id") final Integer id) {
        final Doctor doctor = doctorService.get(id);
        final RatingSummary summary = ratingService.getDoctorSummary(doctor);

        return ok(new RatingSummaryDTO(summary));
    }

    @GET
    @Path("/{id}/ratings")
    public Response patientRating(@PathParam("id") final Integer id) {
        final Doctor doctor = doctorService.get(id);
        final Patient patient = getLoggedUser().getPatient();
        if (patient == null) {
            return badRequest("Debe ser un paciente para puntuar a un doctor");
        }
        final Rating rating = ratingService.get(doctor, patient);

        if (rating == null)
            return notFound();

        return ok(new RatingDTO(rating));
    }

    @PUT
    @Path("/{id}/ratings")
    public Response createPatientRating(@PathParam("id") final Integer id, final RatingParams params) {
        final Doctor doctor = doctorService.get(id);
        final Patient patient = getLoggedUser().getPatient();
        if (patient == null) {
            return badRequest("Debe ser un paciente para puntuar a un doctor");
        }
        final Rating rating = ratingService.update(doctor, patient, params.value);

        return ok(new RatingDTO(rating));
    }
}
