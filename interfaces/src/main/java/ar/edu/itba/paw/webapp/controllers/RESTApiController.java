package ar.edu.itba.paw.webapp.controllers;

public interface RESTApiController {
	public Object create() throws MethodNotAllowedException;
	public Object list() throws MethodNotAllowedException;
	public Object update(final Integer id) throws MethodNotAllowedException;
	public Object show(final Integer id) throws MethodNotAllowedException;
    public Object delete(final Integer id) throws MethodNotAllowedException;
}
