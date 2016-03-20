package ar.edu.itba.paw.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class AbstractRESTApiController implements RESTApiController {
	
	@RequestMapping(method = RequestMethod.POST)
	@Override
	public Object create() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@Override
	public Object list() {
		return null;
	}

	@RequestMapping(path = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	@Override
	public Object update(int id) {
		return null;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@Override
	public Object show(int id) {
		return null;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@Override
	public Object delete(int id) {
		return null;
	}

}
