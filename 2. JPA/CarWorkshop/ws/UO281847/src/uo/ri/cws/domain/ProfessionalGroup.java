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
 * Titulo: Clase ProfessionalGroup
 * Descripción: Contiene la entidad que corresponde al grupo profesional 
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 nov 2022
 */
@Entity
@Table(name = "TProfessionalGroups")
public class ProfessionalGroup extends BaseEntity{
	// natural attributes
	/**
	 * Atributo name
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String name;
	/**
	 * Atributo productivityBonus
	 */
	@Column(name = "productivityBonusPercentage")
	private double productivityBonus;
	/**
	 * Atributo trienniumPayment
	 */
	private double trienniumPayment;
	
	// accidental attributes
	/**
	 * Atributo contracts
	 */
	@OneToMany(mappedBy = "professionalGroup")
	private Set<Contract> contracts = new HashSet<>();

	/**
	 * Constructor sin parámetros de la clase ProfessionalGroup
	 */
	ProfessionalGroup() {}
	
	/**
	 * Constructor con el nombre del grupo profesional como parámetro de la 
	 * clase ProfessionalGroup
	 * 
	 * @param name, nombre del grupo profesional
	 */
	public ProfessionalGroup(String name) {
		this(name, 0, 0);
	}

	/**
	 * Constructor con el nombre del grupo profesional, el bonus de 
	 * productividad y el pago trimestral como parámetros de la clase ProfessionalGroup
	 * 
	 * @param name, nombre del grupo profesional
	 * @param productivityBonus, bonus de productividad del grupo profesional
	 * @param trienniumPayment, pago trimestral del grupo profesional
	 */
	public ProfessionalGroup(String name, double trienniumPayment, double productivityBonus) {
		ArgumentChecks.isNotBlank(name, "El nombre del grupo profesional no puede ser null ni estar vacío");
		ArgumentChecks.isTrue(productivityBonus >= 0, "El bonus de productividad debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(trienniumPayment >= 0, "El pago trimestral debe ser mayor o igual que cero");
		
		this.name = name;		
		this.trienniumPayment = trienniumPayment;
		this.productivityBonus = productivityBonus;
	}

	/**
	 * Método getName
	 * Devuelve el nombre del grupo profesional
	 * 
	 * @return name, nombre del grupo profesional
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setName
	 * Modifica el nombre del grupo profesional
	 * 
	 * @param name, nombre del grupo profesional
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método getProductivityBonus
	 * Devuelve el bonus de productividad del grupo profesional
	 * 
	 * @return productivityBonus, bonus de productividad del grupo profesional
	 */
	public double getProductivityBonus() {
		return productivityBonus;
	}

	/**
	 * Método setProductivityBonus
	 * Modifica el bonus de productividad del grupo profesional
	 * 
	 * @param productivityBonus, bonus de productividad del grupo profesional
	 */
	public void setProductivityBonus(double productivityBonus) {
		this.productivityBonus = productivityBonus;
	}

	/**
	 * Método getTrienniumPayment
	 * Devuelve el pago trimestral del grupo profesional
	 * 
	 * @return trienniumPayment, pago trimestral del grupo profesional
	 */
	public double getTrienniumPayment() {
		return trienniumPayment;
	}

	/**
	 * Método setTrienniumPayment
	 * Modifica el pago trimestral del grupo profesional
	 * 
	 * @param trienniumPayment, pago trimestral del grupo profesional
	 */
	public void setTrienniumPayment(double trienniumPayment) {
		this.trienniumPayment = trienniumPayment;
	}
	
	/**
	 * Método getContracts
	 * Devuelve los contratos del grupo profesional
	 * 
	 * @return contracts, contratos del grupo profesional
	 */
	public Set<Contract> getContracts() {
		return new HashSet<Contract>(contracts);
	}

	/**
	 * Método _getContracts
	 * Devuelve los contratos del grupo profesional para modificarlos
	 * (añadir o eliminar)
	 * 
	 * @return contracts, contratos del grupo profesional
	 */
	Set<Contract> _getContracts() {
		return contracts;
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
		result = prime * result + Objects.hash(name);
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
		ProfessionalGroup other = (ProfessionalGroup) obj;
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
		return "ProfessionalGroup [name=" + name + ", productivityBonus=" + productivityBonus + ", trienniumPayment="
				+ trienniumPayment + "]";
	}	
}