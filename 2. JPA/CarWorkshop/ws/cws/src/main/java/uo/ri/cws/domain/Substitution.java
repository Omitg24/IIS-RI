package uo.ri.cws.domain;

import java.util.Objects;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Substitution
 * Descripción: Contiene la entidad que corresponde a la sustitución 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
public class Substitution extends BaseEntity {
	// natural attributes
	/**
	 * Atributo quantity
	 */
	private int quantity;

	// accidental attributes
	/**
	 * Atributo sparePart
	 */
	private SparePart sparePart;
	/**
	 * Atributo intervention
	 */
	private Intervention intervention;

	/**
	 * Constructor sin parámetros de la clase Substitution
	 */
	Substitution() {}

	/**
	 * Constructor con la pieza de repuesto, la intervención y la cantidad de la
	 * sustitución como parámetros de la clase Substitution
	 * 
	 * @param sparePart, pieza de repuesto
	 * @param intervention, intervención
	 * @param quantity, cantidad
	 */
	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		ArgumentChecks.isNotNull(sparePart, "La pieza de repuesto no puede ser null");
		ArgumentChecks.isNotNull(intervention, "La intervención no puede ser null");
		ArgumentChecks.isTrue(quantity > 0, "La cantidad debe de ser mayor que cero");

		this.sparePart = sparePart;
		this.intervention = intervention;
		this.quantity = quantity;
		
		Associations.Substitute.link(sparePart, this, intervention);
	}

	/**
	 * Método getAmount
	 * Devuelve la cantidad de la sustitución
	 * 
	 * @retur amount, cantidad  de la sustitución
	 */
	public double getAmount() {
		return quantity * sparePart.getPrice();
	}
	
	/**
	 * Método getSparePart
	 * Devuelve la pieza de repuesto de la sustitución
	 * 
	 * @return sparePart, pieza de repuesto de la sustitución
	 */
	public SparePart getSparePart() {
		return sparePart;
	}
	
	/**
	 * Método _setSparePart
	 * Modifica la pieza de repuesto de la sustitución
	 * 
	 * @param sparePart, pieza de repuesto de la sustitución
	 */
	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}
	
	/**
	 * Método getIntervention
	 * Devuelve la intervención de la sustitución
	 * 
	 * @return intervention, intervención de la sustitución
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * Método _setIntervention
	 * Modifica la intervención de la sustitución
	 * 
	 * @param intervention, interveción de la sustitución
	 */
	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	/**
	 * Método getQuantity
	 * Devuelve la cantidad de la sustitución
	 * 
	 * @return quantity, cantidad de la sustitución
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Método setQuantity
	 * Modifica la cantidad de la sustitución
	 * 
	 * @param quantity. cantidad de la sustitución
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		result = prime * result + Objects.hash(intervention, sparePart);
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
		Substitution other = (Substitution) obj;
		return Objects.equals(intervention, other.intervention) && Objects.equals(sparePart, other.sparePart);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Substitution [quantity=" + quantity + ", sparePart=" + sparePart + ", intervention=" + intervention
				+ "]";
	}
}
