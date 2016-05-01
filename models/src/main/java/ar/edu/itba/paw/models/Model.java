package ar.edu.itba.paw.models;

public class Model {
	private final int id;
	
	public Model(int id){
		this.id=id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
