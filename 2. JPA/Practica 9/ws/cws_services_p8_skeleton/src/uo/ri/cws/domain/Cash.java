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
}
