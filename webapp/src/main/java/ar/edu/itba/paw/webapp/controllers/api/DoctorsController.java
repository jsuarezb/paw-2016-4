package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.RatingService;
import ar.edu.itba.paw.webapp.dto.DoctorDTO;
import ar.edu.itba.paw.webapp.dto.RatingDTO;
import ar.edu.itba.paw.webapp.dto.RatingSummaryDTO;
import ar.edu.itba.paw.webapp.params.PaginationHelper;
import ar.edu.itba.paw.webapp.params.RatingParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1/doctors")
@Component
public class DoctorsController extends ApiController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RatingService ratingService;

    @GET
    public Response index(@QueryParam("first_name") final String firstName,
                          @QueryParam("last_name") final String lastName,
                          @QueryParam("speciality_id") final Integer speciality_id,
                          @QueryParam("page") final Integer page) {
        List<Doctor> doctors;
        if (speciality_id != null) {
            doctors = doctorService.searchBySpeciality(speciality_id);
        } else {
            if (firstName == null && lastName == null) {
                doctors = doctorService.getAll();
            } else {
                doctors = doctorService.searchByName(firstName, lastName);
            }
        }

        final GenericEntity<List<DoctorDTO>> list = new GenericEntity<List<DoctorDTO>>(DoctorDTO.fromList(doctors)) {
        };
        return ok(list);
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
        final Patient patient = (Patient) getLoggedUser();
        final Rating rating = ratingService.get(doctor, patient);

        if (rating == null)
            return notFound();

        return ok(new RatingDTO(rating));
    }

    @PUT
    @Path("/{id}/ratings")
    public Response createPatientRating(@PathParam("id") final Integer id, final RatingParams params) {
        final Doctor doctor = doctorService.get(id);
        final Patient patient = (Patient) getLoggedUser();
        final Rating rating = ratingService.update(doctor, patient, params.value);

        return ok(new RatingDTO(rating));
    }
}
