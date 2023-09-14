package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Cash 
 * Descripción: Contiene la entidad que corresponde al pago en efectivo
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TCashes")
public class Cash extends PaymentMean {
	/**
	 * Constructor sin parámetro de la clase Cash
	 */
	Cash() {}

	/**
	 * Constructor con el cliente como parámetro de la clase Cash
	 * 
	 * @param client, cliente
	 */
	public Cash(Client client) {
		ArgumentChecks.isNotNull(client, "El cliente no puede ser null");
		
		Associations.Pay.link(client, this);
	}

	/**
	 * Método equals 
	 * Devuelve true si dos objetos son iguales
	 * 
	 * @return true o false, en función de si los objetos son iguales
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
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
		return "Cash [getAccumulated()=" + getAccumulated() + ", getClient()=" + getClient() + "]";
	}		
}
