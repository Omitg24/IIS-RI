package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Vehicle
 * Descripción: Contiene la entidad que corresponde al vehiculo 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TVehicles")
public class Vehicle extends BaseEntity {
	// natural attributes
	/**
	 * Atributo plateNumber
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String plateNumber;
	/**
	 * Atributo make
	 */
	@Basic(optional = false)
	@Column(name = "BRAND")
	private String make;
	/**
	 * Atributo model
	 */
	@Basic(optional = false)
	private String model;
	
	// accidental attributes
	/**
	 * Atributo client
	 */
	@ManyToOne(optional = false)
	private Client client;
	/**
	 * Atributo type
	 */
	@ManyToOne(optional = false)
	private VehicleType type;
	/**
	 * Atributo workOrders
	 */
	@OneToMany(mappedBy = "vehicle")
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

	/**
	 * Constructor sin parámetros de la clase Vehicle
	 */
	Vehicle() {}

	/**
	 * Constructor con el número de matrícula del vehicle como parámetro de la 
	 * clase Vehicle
	 * 
	 * @param plateNumber, número de matricula
	 */
	public Vehicle(String plateNumber) {
		this(plateNumber, "no-make", "no-model");
	}

	/**
	 * Constructor con el número de matrícula, marca y modelo como parámetros de 
	 * la clase Vehicle
	 * 
	 * @param plateNumber, número de matricula
	 * @param make, marca del vehiculo
	 * @param model, modelo del vehiculo
	 */
	public Vehicle(String plateNumber, String make, String model) {
		ArgumentChecks.isNotBlank(plateNumber, "El número de matricula del vehiculo no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(make, "La marca del vehiculo no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(model, "El modelo del vehiculo no puede ser null ni estar vacío");

		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
	}

	/**
	 * Método getPlateNumber
	 * Devuelve el número de matrícula del vehiculo
	 * 
	 * @return plateNumber, número de matrícula del vehiculo
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * Método setPlateNumber
	 * Modifica el número de matrícula del vehiculo
	 * 
	 * @param plateNumber, número de matrícula del vehiculo
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/**
	 * Método getMake
	 * Devuelve la marca del vehiculo
	 * 
	 * @return make, marca del vehiculo
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Método setMake
	 * Modifica la marca del vehiculo
	 * 
	 * @param make, marca del vehiculo
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Método getModel
	 * Devuelve el modelo del vehiculo
	 * 
	 * @return model, modelo del vehiculo
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Método setModel
	 * Modifica el modelo del vehiculo
	 * 
	 * @param model, modelo del vehiculo
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Método getClient
	 * Devuelve el cliente del vehiculo
	 * 
	 * @return client, cliente del vehiculo
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Método setClient
	 * Modifica el cliente del vehiculo
	 * 
	 * @param client, cliente del vehiculo
	 */
	void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Método getType
	 * Devuelve el tipo del vehiculo
	 * 
	 * @return type, tipo del vehiculo
	 */
	public VehicleType getType() {
		return type;
	}

	/**
	 * Método setType
	 * Modifica el tipo del vehiculo
	 * 
	 * @param type, tipos del vehiculo
	 */
	void setType(VehicleType type) {
		this.type = type;
	}
	
	/**
	 * Método getWorkOrders
	 * Devuelve las averías del vehiculo
	 * 
	 * @return workOrders, averías del vehiculo
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workOrders);
	}

	/**
	 * Método _getWorkOrders
	 * Devuelve las averías del vehiculo para modificarlas (añadir o eliminar)
	 * 
	 * @return workOrders, averías del vehiculo
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		return Objects.hash(plateNumber);
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
		Vehicle other = (Vehicle) obj;
		return Objects.equals(plateNumber, other.plateNumber);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make + ", model=" + model + "]";
	}
}
