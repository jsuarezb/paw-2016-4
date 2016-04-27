package ar.edu.itba.paw;


/**
 * Created by agophurmuz on 4/20/16.
 */
public class Speciality {
    private final int id;
    private final String name;

    public Speciality(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Speciality speciality = (Speciality) o;

        return id == speciality.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
