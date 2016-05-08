package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.webapp.controllers.BadRequestException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Generall exception handler for our webapp
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound() {
        return "not_found_404";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequest() {
        // TODO: check if there's a better object to answer rather than null
        return null;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.class)
    public String handleInternalServerError() {
        return "internal_error_500";
    }

}
