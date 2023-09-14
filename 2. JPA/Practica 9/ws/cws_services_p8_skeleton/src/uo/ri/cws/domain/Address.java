package uo.ri.cws.domain;

import javax.persistence.Embeddable;

import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Address 
 * Descripción: Contiene la dirección (calle, ciudad y codigo postal) 
 * de la entidad que la utilice
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Embeddable
public class Address {
	/**
	 * Atributo street
	 */
	private String street;
	/**
	 * Atributo city
	 */
	private String city;
	/**
	 * Atributo zipCode
	 */
	private String zipCode;

	/**
	 * Constructor sin parámetros de la clas Address
	 */
	Address() {
		this("no-street", "no-city", "no-zip");
	}

	/**
	 * Constructor con todos los parámetros de la clase Address
	 * 
	 * @param street,  calle de la dirección
	 * @param city,    ciudad de la dirección
	 * @param zipCode, codigo postal de la dirección
	 */
	public Address(String street, String city, String zipCode) {
		ArgumentChecks.isNotEmpty(street);
		ArgumentChecks.isNotEmpty(city);
		ArgumentChecks.isNotEmpty(zipCode);

		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}

	/**
	 * Método getStreet 
	 * Devuelve la calle de la dirección
	 * 
	 * @return street, calle de la dirección
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Método getCity 
	 * Devuelve la ciudad de la dirección
	 * 
	 * @return city, ciudad de la dirección
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Método getZipCode 
	 * Devuelve el codigo postal de la dirección
	 * 
	 * @return zipCode, codigo postal de la dirección
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zipCode=" + zipCode + "]";
	}
}
