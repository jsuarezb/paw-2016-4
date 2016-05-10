package ar.edu.itba.paw.models;

public class PatientPhone {

    private final int patientId;
    private final String phone;

    public PatientPhone(int patientId, String phone) {
        this.patientId = patientId;
        this.phone = phone;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientPhone that = (PatientPhone) o;

        return phone.equals(that.phone);

    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

    @Override
    public String toString() {
        return phone;
    }
}
