package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.ResponseList;
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
@Path("api/v1/neighborhoods")
@Component
public class NeighborhoodController extends ApiController {

    @Autowired
    private AddressService addressService;

    @GET
    public Response listSpecialities() {
        final List<String> allNeighborhoods = addressService.getAllNeighborhoods();
        return Response.ok(new ResponseList(allNeighborhoods)).build();
    }
}
