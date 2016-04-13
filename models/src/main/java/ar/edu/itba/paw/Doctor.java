package ar.edu.itba.paw;

/**
 * Created by santi698 on 24/03/16.
 */
public class Doctor {
    private final int id;
    private final String name;
    private final String last_name;
    private final String speciality;
    private final String email;
    private final String password;

    public Doctor(int id, String name, String last_name, String speciality, String email, String password) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.speciality = speciality;
        this.email = email;
        this.password = password;
    }

	public String getName() {
		return name;
	}

    public int getId() {
        return id;
    }

    public String getLastName() {
        return last_name;
    }

	public String getSpeciality() {
		return speciality;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Doctor doctor = (Doctor) o;

        return id == doctor.id;
	}

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
