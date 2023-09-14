package uo.ri.cws.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

/**
 * Titulo: Clase Voucher 
 * Descripción: Contiene la entidad que corresponde al pago con bono
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TVouchers")
public class Voucher extends PaymentMean {
	/**
	 * Atributo code
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String code;
	/**
	 * Atributo available
	 */
	private double available = 0.0;
	/**
	 * Atributo description
	 */
	private String description;

	/**
	 * Constructor sin parámetros de la clase Voucher
	 */
	Voucher() {}

	/**
	 * Constructor con parámetro code y available de la clase Voucher
	 * 
	 * @param code
	 */
	public Voucher(String code, double available) {		
		this(code, "no-description", available);		
	}

	/**
	 * Constructor con todos los parámetros de la clase Voucher
	 * @param code
	 * @param available
	 * @param description
	 */
	public Voucher(String code, String description, double available) {		
		ArgumentChecks.isNotBlank(code, "El código del bono no puede ser null o estar vacío");
		ArgumentChecks.isNotBlank(description, "La descripción del bono no puede ser null o estar vacía");
		ArgumentChecks.isTrue(available >= 0, "La cantidad del bono debe de ser mayor o igual a cero");
		
		this.code = code;		
		this.description = description;
		this.available = available;
	}

	/**
	 * Auments the accumulated (super.pay(amount) ) and decrements the available
	 *
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {
		StateChecks.isTrue(amount <= available);
		super.pay(amount);
		this.available -= amount;
	}
	
	/**
	 * Método getCode
	 * Devuelve el código del bono
	 * 
	 * @return code, código del bono
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Método setCode
	 * Modifica el código del bono
	 * 
	 * @param code, código del bono
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Método getAvailable
	 * Devuelve la disponibilidad del bono
	 * 
	 * @return available, disponibilidad del bono
	 */
	public double getAvailable() {
		return available;
	}

	/**
	 * Método setAvailable
	 * Modifica la disponibilidad del bono
	 * 
	 * @param available, disponibilidad del bono
	 */
	public void setAvailable(double available) {
		this.available = available;
	}

	/**
	 * Método getDescription
	 * Devuelve la descipción del bono
	 *  
	 * @return description, descipción del bono
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Método setDescription
	 * Modifica la description del bono
	 * 
	 * @param description, descipción del bono
	 */
	public void setDescription(String description) {
		this.description = description;
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
		result = prime * result + Objects.hash(code);
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
		Voucher other = (Voucher) obj;
		return Objects.equals(code, other.code);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Voucher [code=" + code + ", available=" + available + ", description=" + description + "]";
	}
}
