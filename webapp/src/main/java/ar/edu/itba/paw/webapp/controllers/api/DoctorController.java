package ar.edu.itba.paw.webapp.controllers.api;


import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.controllers.api.AbstractRESTApiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/doctors")
@Controller
public class DoctorController extends AbstractRESTApiController {

    public ModelAndView list() throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
    }
    
    public ModelAndView create() throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
    }

    public ModelAndView show(@PathVariable final Integer id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
    }

    public ModelAndView update(@PathVariable final Integer id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
    }
    
    public ModelAndView delete(@PathVariable final Integer id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
    }

}
