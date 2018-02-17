package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.services.AddressService;
import ar.edu.itba.paw.webapp.dto.NeighborhoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
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
        final GenericEntity<List<NeighborhoodDTO>> list = new GenericEntity<List<NeighborhoodDTO>>(NeighborhoodDTO.fromList(allNeighborhoods)) {
        };
        return ok(list);
    }
}
