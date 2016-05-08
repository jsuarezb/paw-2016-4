package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by santi698 on 08/05/16.
 */
public abstract class BaseController {
    @Autowired
    private PatientService ps;

    @ModelAttribute("patient")
    public Patient currentPatient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().equals("anonymousUser")) {
            User user = (User) auth.getPrincipal();
            return ps.findByEmail(user.getUsername());
        }
        return null;
    }
}
