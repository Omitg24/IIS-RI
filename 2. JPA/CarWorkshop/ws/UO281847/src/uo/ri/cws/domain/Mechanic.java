package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Mechanic
 * Descripción: Contiene la entidad que corresponde al mecánico 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TMechanics")
public class Mechanic extends BaseEntity {
	// natural attributes
	/**
	 * Atributo dni
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String dni;
	/**
	 * Atributo name
	 */
	@Basic(optional = false)
	private String name;
	/**
	 * Atributo surname
	 */
	@Basic(optional = false)
	private String surname;

	// accidental attributes
	/**
	 * Atributo assigned
	 */
	@OneToMany(mappedBy = "mechanic")
	private Set<WorkOrder> assigned = new HashSet<>();
	/**
	 * Atributo interventions
	 */
	@OneToMany(mappedBy = "mechanic")
	private Set<Intervention> interventions = new HashSet<>();
	/**
	 * Atributo contractInForce
	 */
	@OneToOne(mappedBy = "mechanic")
	private Contract contractInForce;
	/**
	 * Atributo terminatedContracts
	 */
	@OneToMany(mappedBy = "firedMechanic")
	private Set<Contract> terminatedContracts = new HashSet<>();
	
	/**
	 * Constructor sin parámetros de la clase Mechanic
	 */
	Mechanic() {}

	/**
	 * Constructor con el dni del mecánico como parámetro de la clase Mechanic
	 * 
	 * @param dni, dni del mecánico
	 */
	public Mechanic(String dni) {
		this(dni, "no-surname", "no-name");
	}

	/**
	 * Constructor con el dni, nombre y apellidos del mecánico como parámetros 
	 * de la clase Mechanic
	 * 
	 * @param dni, dni del mecánico
	 * @param name, nombre del mecánico
	 * @param surname, apellidos del mecánico
	 */
	public Mechanic(String dni, String name, String surname) {
		ArgumentChecks.isNotBlank(dni, "El dni del mecánico no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(name, "El nombre del mecánico no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(surname, "El apellido del mecánico no puede ser null ni estar vacío");

		this.dni = dni;
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Método isInForce
	 * Devuelve true o false en función de si el contrato del mecánico está en 
	 * vigor (IN_FORCE)
	 * 
	 * @return true o false, en función de si el contrato está en vigor
	 */
	public boolean isInForce() {
		return contractInForce.isInForce();
	}
	
	/**
	 * Método getDni
	 * Devuelve el dni del mecánico
	 * 
	 * @return dni, dni del mecánico
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Método setDni
	 * Modifica el dni del mecánico
	 * 
	 * @param dni, dni del mecánico
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Método getName
	 * Devuelve el nombre del mecánico
	 * 
	 * @return name, nombre del mecánico
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setName
	 * Modifica el nombre del mecánico
	 * 
	 * @param name, nombre del mecánico
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Método getSurname
	 * Devuelve los apellidos del mecánico
	 * 
	 * @return surname, apellidos del mecánico
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Método setSurname
	 * Modifica los apellidos del mecánico
	 * 
	 * @param surname, apellidos del mecánico
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Método getAssigned
	 * Devuelve las averías asignadas
	 * 
	 * @return assigned, averías asignadas
	 */
	public Set<WorkOrder> getAssigned() {
		return new HashSet<>(assigned);
	}

	/**
	 * Método _getAssigned
	 * Devuelve las averías asignadas del mecánico para modificarlas
	 * (añadir o eliminar)
	 * 
	 * @return assigned, averías asignadas del mecánico
	 */
	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	/**
	 * Método getInterventions
	 * Devuelve las intervenciones del mecánico
	 * 
	 * @return interventions, intervenciones del mecánico
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<>(interventions);
	}

	/**
	 * Método _getInterventions
	 * Devuelve las intervenciones del mecánico para modificarlas 
	 * (añadir o eliminar)
	 * 
	 * @return interventions, intervenciones del mecánico
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}
	
	/**
	 * Método getContractInForce
	 * Devuelve el contrato en vigor
	 * 
	 * @return contract, contrato en vigor
	 */
	public Optional<Contract> getContractInForce() {
		return Optional.ofNullable(contractInForce);
	}

	/**
	 * Método setContractInForce
	 * Modifica el contrato en vigor
	 * 
	 * @param contractInForce, contrato en vigor
	 */
	public void setContractInForce(Contract contractInForce) {
		this.contractInForce = contractInForce;
	}

	/**
	 * Método getFiredContracts
	 * Devuelve los contrados de despido
	 * 
	 * @return terminatedContracts, contratos de despido
	 */
	public Set<Contract> getTerminatedContracts() {
		return new HashSet<>(terminatedContracts);
	}

	/**
	 * Método _getFiredContracts
	 * Devuelve los contrados de despido para modificarlos (añadir o eliminar)
	 * 
	 * @return terminatedContracts, contratos de despido
	 */
	public Set<Contract> _getTerminatedContracts() {
		return terminatedContracts;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	/**
	 * Método equals 
	 * Devuelve true si dos objetos son iguales
	 * 
	 * @return true o false, en función de si los objetos son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mechanic other = (Mechanic) obj;
		return Objects.equals(dni, other.dni);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}
}