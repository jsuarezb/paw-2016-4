package ar.edu.itba.paw.controllers;

public interface RESTApiController {
	public Object create();
	public Object list();
	public Object update(final int id);
	public Object show(final int id); 
    public Object delete(final int id);

}
