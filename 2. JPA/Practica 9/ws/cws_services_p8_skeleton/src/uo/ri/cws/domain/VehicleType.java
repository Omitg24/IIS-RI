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

/**
 * Titulo: Clase VehicleType
 * Descripción: Contiene la entidad que corresponde al tipo de vehiculo 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TVehicleTypes")
public class VehicleType extends BaseEntity {
	// natural attributes
	/**
	 * Atributo name
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String name;
	/**
	 * Atributo pricePerHour
	 */
	@Basic(optional = false)
	private double pricePerHour;

	// accidental attributes
	/**
	 * Atributo vehicles
	 */
	@OneToMany(mappedBy = "type")
	private Set<Vehicle> vehicles = new HashSet<>();

	/**
	 * Constructor sin parámetros de la clase VehicleType
	 */
	VehicleType() {}

	/**
	 * Construcor con el nombre del tipo de vehiculo como parámetro de la 
	 * clase VehicleTypeDto
	 * 
	 * @param name, nombre del tipo de vehiculo
	 */
	public VehicleType(String name) {
		this(name, 0);
	}

	/**
	 * Constructor con el nombre y el precio por hora del tipo de vehiculo como 
	 * parámetros de la clase VehicleType
	 * 
	 * @param name, nombre del tipo de vehiculo
	 * @param pricePerHour, precio por hora del tipo de vehiculo
	 */
	public VehicleType(String name, double pricePerHour) {
		ArgumentChecks.isNotBlank(name, "El nombre del tipo de vehiculo no puede ser null ni estar vacío");
		ArgumentChecks.isTrue(pricePerHour >= 0, "El precio por hora del tipo de vehiculo debe ser mayor o igual que cero");

		this.name = name;
		this.pricePerHour = pricePerHour;
	}

	/**
	 * Método getName
	 * Devuelve el nombre del tipo de vehiculo
	 * 
	 * @return name, nombre del tipo de vehiculo
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setName 
	 * Modifica el nombre del tipo de vehiculo
	 * 
	 * @param name, nombre del tipo de vehiculo
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método getPricePerHour
	 * Devuelve el precio por hora del tipo de vehiculo
	 * 
	 * @return pricePerHour, precio por hora del tipo de vehiculo
	 */
	public double getPricePerHour() {
		return pricePerHour;
	}

	/**
	 * Método setPricePerHour
	 * Modifica el precio por hora del tipo de vehiculo
	 * 
	 * @param pricePerHour, precio por hora del tipo de vehiculo
	 */
	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	/**
	 * Método getVehicles
	 * Devuelve los vehiculos que tienen el tipo de vehiculo
	 * 
	 * @return vehicles, vehiculos que tienen el tipo de vehiculo
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}

	/**
	 * Método _getVehicles
	 * Devuelve los vehiculos que tienen el tipo de vehiculo para modificarlos 
	 * (añadir o eliminar)
	 * 
	 * @return vehicles, vehiculos que tienen el tipo de vehiculo
	 */
	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		VehicleType other = (VehicleType) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour + "]";
	}
}
