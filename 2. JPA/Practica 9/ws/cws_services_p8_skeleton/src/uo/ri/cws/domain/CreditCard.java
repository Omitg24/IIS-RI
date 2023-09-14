package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase CreditCard 
 * Descripción: Contiene la entidad que corresponde al pago con tarjeta 
 * de crédito
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TCreditCards")
public class CreditCard extends PaymentMean {
	/**
	 * Atributo number
	 */
	@Basic(optional = false)
	private String number;
	/**
	 * Atributo type
	 */
	@Basic(optional = false)
	private String type;
	/**
	 * Atributo validThru
	 */
	@Basic(optional = false)
	private LocalDate validThru;

	/**
	 * Constructor sin parámetros de la clase CreditCard
	 */
	CreditCard() {}

	/**
	 * Constructor con el número de la tarjeta como parámetro de la clase CreditCard
	 * 
	 * @param number, número de la tarjeta
	 */
	public CreditCard(String number) {
		this(number, "no-type", LocalDate.now().plusDays(1));
	}

	/**
	 * Constructor con todos los parámetros de la clase CreditCard
	 * 
	 * @param number,    número de la tarjeta
	 * @param type,      tipo de la tarjeta
	 * @param validThru, fecha de validez de la tarjeta
	 */
	public CreditCard(String number, String type, LocalDate validThru) {
		ArgumentChecks.isNotEmpty(number, "El número de la tarjeta no puede ser null ni estar vacío");
		ArgumentChecks.isNotEmpty(type, "El tipo de la tarjeta no puede ser null ni estar vacío");
		ArgumentChecks.isNotNull(validThru, "La fecha de validez de la tarjeta no puede ser null");
//    	if (validThru.getYear() < LocalDate.now().getYear()) {
//    		throw new IllegalArgumentException("La fecha de validez de la tarjeta debe ser posterior a la fecha actual");
//    	}    	

		this.number = number;
		this.type = type;
		this.validThru = validThru;
	}

	/**
	 * Método isValidNow
	 * Devuelve true o false en función de si la tarjeta es válida
	 * 
	 * @return true o false, si la tarjeta es válida
	 */
	public boolean isValidNow() {
		return validThru.isAfter(LocalDate.now());
	}
	
	/**
	 * Método getNumber 
	 * Devuelve el número de la tarjeta
	 * 
	 * @return number, número de la tarjeta
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Método setNumber 
	 * Modifica el número de la tarjeta
	 * 
	 * @param number, número de la tarjeta
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Método getType 
	 * Devuelve el tipo de la tarjeta
	 * 
	 * @return type, tipo de la tarjeta
	 */
	public String getType() {
		return type;
	}

	/**
	 * Método setType 
	 * Modifica el tipo de la tarjeta
	 * 
	 * @param type, tipo de la tarjeta
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Método getValidThru 
	 * Devuelve la fecha de validez de la tarjeta
	 * 
	 * @return validThru, fecha de validez de la tarjeta
	 */
	public LocalDate getValidThru() {
		return validThru;
	}

	/**
	 * Método setValidThru 
	 * Modifica la fecha de validez de la tarjeta
	 * 
	 * @param validThru, fecha de validez de la tarjeta
	 */
	public void setValidThru(LocalDate validThru) {
		this.validThru = validThru;
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
		int result = super.hashCode();
		result = prime * result + Objects.hash(number, type, validThru);
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(number, other.number) && Objects.equals(type, other.type)
				&& Objects.equals(validThru, other.validThru);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", type=" + type + ", validThru=" + validThru + "]";
	}
}
