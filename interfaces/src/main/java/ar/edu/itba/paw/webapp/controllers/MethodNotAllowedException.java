package ar.edu.itba.paw.webapp.controllers;

public class MethodNotAllowedException extends Exception {

	private static final long serialVersionUID = -135629360486592116L;

	public MethodNotAllowedException() {
		super("Method not allowed.");
	}
	public MethodNotAllowedException(String string) {
		super(string);
	}
}
