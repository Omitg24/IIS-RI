package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;

/**
 * Titulo: Clase PaymentMean 
 * Descripción: Contiene la entidad que coresponde al método de pago, del que
 * heredarán clases como Cash, CreditCard y Voucher
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
public abstract class PaymentMean extends BaseEntity {
	// natural attributes
	/**
	 * Atributo accumulated
	 */
	private double accumulated = 0.0;

	// accidental attributes
	/**
	 * Atributo client
	 */
	private Client client;
	/**
	 * Atributo charges
	 */
	private Set<Charge> charges = new HashSet<>();

	/**
	 * Método getAccumulated 
	 * Devuelve el acumulado del método de pago
	 * 
	 * @return accumulated, acumulado del método de pago
	 */
	public double getAccumulated() {
		return accumulated;
	}

	/**
	 * Método setAccumulated 
	 * Modifica el acumulado del método de pago
	 * 
	 * @param accumulated, acumulado del método de pago
	 */
	public void setAccumulated(double accumulated) {
		this.accumulated = accumulated;
	}

	/**
	 * Método pay 
	 * Paga el importe pasado por parámetro
	 * 
	 * @param importe, importe a pagar
	 */
	public void pay(double importe) {
		this.accumulated += importe;
	}
	
	/**
	 * Método isEnough
	 * Devuelve true si se puede pagar el valor pasado por parámetro
	 * 
	 * @param value, valor
	 * @return true o false, si se puede pagar
	 */
	public boolean isEnough(Double value) {
		return accumulated >= value;
	}

	/**
	 * Método _setClient 
	 * Modifica el cliente que va a utilizar el método de pago
	 * 
	 * @param client, cliente
	 */
	void _setClient(Client client) {
		this.client = client;
	}

	/**
	 * Método getClient 
	 * Devuelve el cliente que utiliza el método de pago
	 * 
	 * @return client, cliente que utiliza el método de pago
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Método getCharges 
	 * Devuelve los cargos a pagar
	 * 
	 * @return charges, cargos a pagar
	 */
	public Set<Charge> getCharges() {
		return new HashSet<>(charges);
	}

	/**
	 * Método _getCharges 
	 * Devuelve los cargos a pagar para modificarlos (añadir o eliminar)
	 * 
	 * @return charges, cargos a pagar
	 */
	Set<Charge> _getCharges() {
		return charges;
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
		result = prime * result + Objects.hash(client);
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
		PaymentMean other = (PaymentMean) obj;
		return Objects.equals(client, other.client);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "PaymentMean [accumulated=" + accumulated + ", client=" + client + "]";
	}
}
