package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.dto.SpecialityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */

@Path("api/v1/specialities")
@Component
public class SpecialitiesController extends ApiController {

    @Autowired
    private SpecialityService specialityService;

    @GET
    public Response index() {
        final List<Speciality> allSpecialities = specialityService.getAll();
        final GenericEntity<List<SpecialityDTO>> list = new GenericEntity<List<SpecialityDTO>>(SpecialityDTO.fromList(allSpecialities)) {
        };
        return ok(list);
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") final Integer id) {
        final Speciality speciality = specialityService.getById(id);

        if (speciality != null) {
            return ok(new SpecialityDTO(speciality));
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
