package ar.edu.itba.paw.models;

public class DoctorPhone {

    private final int doctorId;
    private final String phone;

    public DoctorPhone(final int doctorId, final String phone) {
        this.doctorId = doctorId;
        this.phone = phone;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorPhone that = (DoctorPhone) o;

        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }

    @Override
    public String toString() {
        return phone;
    }
}
