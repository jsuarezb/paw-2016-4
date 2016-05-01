package ar.edu.itba.paw.models;

/**
 * Created by santi698 on 24/03/16.
 */
public class Institution {
    private final String name;
    private final Address address;
    private final int id;

    public Institution(int id, String name, Address address) {
        this.id = id;
    	this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Institution{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

	public int getId() {
		return id;
	}
}
