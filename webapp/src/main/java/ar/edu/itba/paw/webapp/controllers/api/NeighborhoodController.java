package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.NeighborhoodList;
import ar.edu.itba.paw.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */
@Path("neighborhoods")
@Component
public class NeighborhoodController {

    @Autowired
    private AddressService addressService;

    @GET
    @Path("/")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response listSpecialities() {
        final List<String> allNeighborhoods = addressService.getAllNeighborhoods();
        System.out.println(allNeighborhoods.size());
        return Response.ok(new NeighborhoodList(allNeighborhoods)).build();
    }
}
