package ar.edu.itba.paw.models;

/**
 * Created by alebian on 12/10/16.
 */
public interface Loggable {
    public static String DOCTOR = "doctor";
    public static String PATIENT = "patient";

    public String type();
    public String getPassword();
    public String getEmail();
}
