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
 * Titulo: Clase Client
 * Descripción: Contiene la entidad que corresponde al cliente 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TClients")
public class Client extends BaseEntity {
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
	/**
	 * Atributo email
	 */
	private String email;
	/**
	 * Atributo phone
	 */
	private String phone;
	/**
	 * Atributo address
	 */
	private Address address;
	
	// accidental attributes
	/**
	 * Atributo vehicles
	 */
	@OneToMany(mappedBy = "client")
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	/**
	 * Atributo payments
	 */
	@OneToMany(mappedBy = "client")
	private Set<PaymentMean> paymentMeans = new HashSet<PaymentMean>();

	/**
	 * Constructor sin parámetros de la clase Client
	 */
	Client() {}

	/**
	 * Constructor con el dni del cliente como parámetro de la clase Client
	 * 
	 * @param dni, dni del cliente
	 */
	public Client(String dni) {
		this(dni, "no-name", "no-surname", "no-email", "no-phone", new Address("no-street", "no-city", "no-zip"));
	}

	/**
	 * Constructor con el dni, nombre y apellidos del cliente como 
	 * parámetro de la clase Client
	 * 
	 * @param dni, dni del cliente
	 * @param name, nombre del cliente
	 * @param surname, apellidos del cliente
	 */
	public Client(String dni, String name, String surname) {
		this(dni, name, surname, "no-email", "no-phone", new Address("no-street", "no-city", "no-zip"));
	}

	/**
	 * Constructor con el dni, nombre y apellidos del cliente como 
	 * parámetro de la clase Client
	 * 
	 * @param dni, dni del cliente
	 * @param name, nombre del cliente
	 * @param surname, apellidos del cliente
	 * @param email, email del cliente
	 * @param phone, teléfono del cliente
	 * @param address, dirección del cliente
	 */
	public Client(String dni, String name, String surname, String email, String phone, Address address) {
		ArgumentChecks.isNotBlank(dni, "El dni del cliente no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(name, "El nombre del cliente no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(surname, "El apellido del cliente no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(email, "El email del cliente no puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(phone, "El teléfono del cliente no puede ser null ni estar vacío");
		ArgumentChecks.isNotNull(address, "La dirección del cliente no puede ser null");
		
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * Método getDni
	 * Devuelve el dni del cliente
	 * 
	 * @return dni, dni del cliente
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Método setDni
	 * Modifica el dni del cliente
	 * 
	 * @param dni, dni del cliente
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Método getName
	 * Devuelve el nombre del cliente
	 * 
	 * @return name, nombre del cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setName
	 * Modifica el nombre del cliente
	 * 
	 * @param name, nombre del cliente
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método getSurname
	 * Devuelve los apellidos del cliente
	 * 
	 * @return surname, apellidos del cliente
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Método setSurname
	 * Modifica los apellidos del cliente
	 * 
	 * @param surname, apellidos del cliente
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Método getEmail
	 * Devuelve el email del cliente
	 * 
	 * @return email, email del cliente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método setEmail
	 * Modifica el email del cliente
	 * 
	 * @param email, email del cliente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método getPhone
	 * Devuelve el teléfono del cliente
	 * 
	 * @return phone, teléfono del cliente
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Método setPhone
	 * Modifica el teléfono del cliente
	 * 
	 * @param phone, teléfono del cliente
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Método getAddress
	 * Devuelve la dirección del cliente 
	 * 
	 * @return address, dirección del cliente
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Método setAddress
	 * Modifica la dirección del cliente
	 * 
	 * @param address, dirección del cliente
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Método getVehicles
	 * Devuelve los vehiculos del cliente
	 * 
	 * @return vehicles, vehiculos del cliente
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}

	/**
	 * Método _getVehicles
	 * Devuelve los vehiculos del cliente para modificarlas (añadir o eliminar)
	 * 
	 * @return vehicles, vehiculos del cliente
	 */
	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	/**
	 * Método getPayments
	 * Devuelve los métodos de pago del cliente
	 * 
	 * @return payments, métodos de pago del cliente
	 */
	public Set<PaymentMean> getPaymentMeans() {
		return new HashSet<>(paymentMeans);
	}

	/**
	 * Método _getPayments
	 * Devuelve los métodos de pago del cliente para modificarlos (añadir o eliminar)
	 * 
	 * @return payments, métodos de pago del cliente
	 */
	Set<PaymentMean> _getPaymentMeans() {
		return paymentMeans;
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
		Client other = (Client) obj;
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
		return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone="
				+ phone + ", address=" + address + "]";
	}
}
