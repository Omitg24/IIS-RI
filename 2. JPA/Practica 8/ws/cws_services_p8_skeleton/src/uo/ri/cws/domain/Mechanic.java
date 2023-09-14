package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TMechanics")
public class Mechanic extends BaseEntity {
    // natural attributes
    @Column(unique = true)
    private String dni;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String surname;

    // accidental attributes
    @OneToMany(mappedBy = "mechanic")
    private Set<WorkOrder> assigned = new HashSet<>();
    @OneToMany(mappedBy = "mechanic")
    private Set<Intervention> interventions = new HashSet<>();

    Mechanic() {
    }

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
        return new HashSet<>(assigned);
    }

    Set<WorkOrder> _getAssigned() {
        return assigned;
    }

    public Set<Intervention> getInterventions() {
        return new HashSet<>(interventions);
    }

    Set<Intervention> _getInterventions() {
        return interventions;
    }    

    public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
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
