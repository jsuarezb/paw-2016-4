package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.ResponseList;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.models.SpecialityList;
import ar.edu.itba.paw.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */

@Path("specialities")
@Component
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GET
    @Path("/")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response listSpecialities() {
        final List<Speciality> allSpecialities = specialityService.getAll();
        return Response.ok(new SpecialityList(allSpecialities)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getById(@PathParam("id") final Integer id) {
        final Speciality speciality = specialityService.getById(id);

        if (speciality != null) {
            return Response.ok(speciality).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
