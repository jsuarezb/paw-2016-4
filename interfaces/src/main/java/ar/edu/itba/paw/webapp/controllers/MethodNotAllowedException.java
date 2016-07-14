package ar.edu.itba.paw.webapp.controllers;

public class MethodNotAllowedException extends Exception {

	private static final long serialVersionUID = -135629360486592116L;

	public MethodNotAllowedException() {
		this("Method not allowed.");
	}
	public MethodNotAllowedException(final String string) {
		super(string);
	}
}
