package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase ContractType
 * Descripción: Contiene la entidad que corresponde al tipo de contrato 
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 nov 2022
 */
public class ContractType extends BaseEntity{
	// natural attributes
	/**
	 * Atributo name
	 */
	private String name;
	/**
	 * Atributo compensationDays
	 */
	private double compensationDays;
	
	// accidental attributes
	/**
	 * Atributo contracts
	 */
	private Set<Contract> contracts = new HashSet<>();
	
	/**
	 * Constructor sin parámetros de la clase ContractType
	 */
	ContractType() {}

	/**
	 * Constructor con el nombre del tipo de contrato como parámetro de la 
	 * clase ContractType
	 * 
	 * @param name, nombre del tipo de contrato
	 */
	public ContractType(String name) {
		this(name, 0);
	}

	/**
	 * Constructor con el nombre y los días de compensación del tipo de contrato
	 * como parámetros de la clase ContractType
	 * 
	 * @param name, nombre del tipo de contrato
	 * @param compensationDays, días de compensación del tipo de contrato
	 */
	public ContractType(String name, double compensationDays) {
		ArgumentChecks.isNotBlank(name, "El nombre del tipo de contrato no puede ser null ni estar vacío");
		ArgumentChecks.isTrue(compensationDays >= 0, "Los días de compensación del contrato deben ser mayor o igual a cero");
		
		this.name = name;
		this.compensationDays = compensationDays;
	}

	/**
	 * Método getName
	 * Devuelve el nombre del tipo de contrato 
	 * 
	 * @return name, nombre del tipo del contrato
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setName
	 * Modifica el nombre del tipo de contrato
	 * 
	 * @param name, nombre del tipo de contrato
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método getCompensationDays
	 * Devuelve los días de compensación del tipo de contrato
	 * 
	 * @return compensationDays, días de compensación del tipo de contrato
	 */
	public double getCompensationDays() {
		return compensationDays;
	}

	/**
	 * Método setCompensationDays
	 * Modifica los días de compensación del tipo de contrato
	 * 
	 * @param compensationDays, días de compensación del tipo de contrato
	 */
	public void setCompensationDays(double compensationDays) {
		this.compensationDays = compensationDays;
	}

	/**
	 * Método getContracts
	 * Devuelve los contratos que tengan el tipo de contrato
	 * 
	 * @return contracts, contratos que tengan el tipo de contrato
	 */
	public Set<Contract> getContracts() {
		return new HashSet<Contract>(contracts);
	}

	/**
	 * Método getContracts
	 * Devuelve los contratos que tengan el tipo de contrato para modificarlos
	 * (añadir o eliminar)
	 * 
	 * @return contracts, contratos que tengan el tipo de contrato
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
		ContractType other = (ContractType) obj;
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
		return "ContractType [name=" + name + ", compensationDays=" + compensationDays + "]";
	}	
}