package ar.edu.itba.paw.webapp.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractRESTApiController implements RESTApiController {
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@Override
	public Object create() throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@Override
	public Object list() throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	@RequestMapping(path = "/{id}", 
					method = { RequestMethod.PUT, RequestMethod.PATCH }, 
					consumes = "application/json")
	@Override
	public Object update(@PathVariable final Integer id) throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	@RequestMapping(path = "/{id}",
					method = RequestMethod.GET,
					produces = "application/json")
	@Override
	public Object show(@PathVariable final Integer id) throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	@RequestMapping(path = "/{id}",
					method = RequestMethod.DELETE)
	@Override
	public Object delete(@PathVariable final Integer id) throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	@ExceptionHandler(MethodNotAllowedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) 
	public @ResponseBody String handleException(MethodNotAllowedException ex) {
	  return ex.getMessage();
	}
}
