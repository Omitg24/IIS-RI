package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.util.assertion.ArgumentChecks;

public class Mechanic {
	// natural attributes
	private String dni;
	private String name;
	private String surname;
	
	// accidental attributes
	private Set<WorkOrder> assigned = new HashSet<>();
	private Set<Intervention> interventions = new HashSet<>();
	
	
	
	public Mechanic(String dni) {
		this(dni, "no-surname", "no-name");
	}

	
	
	public Mechanic(String dni, String name, String surname) {
		ArgumentChecks.isNotBlank(dni);
		ArgumentChecks.isNotBlank(name);
		ArgumentChecks.isNotBlank(surname);	
		
		this.dni = dni;
		this.name = name;
		this.surname = surname;		
	}



	public Set<WorkOrder> getAssigned() {
		return new HashSet<>( assigned );
	}

	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<>( interventions );
	}

	Set<Intervention> _getInterventions() {
		return interventions;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int hashCode() {
		return Objects.hash(dni, name, surname);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mechanic other = (Mechanic) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}



	@Override
	public String toString() {
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}
	
	

}
