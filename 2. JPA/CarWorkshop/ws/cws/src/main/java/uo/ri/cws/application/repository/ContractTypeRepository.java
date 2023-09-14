package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.ContractType;

/**
 * Título: Clase ContractTypeRepository
 * Descripción: Contiene a la interfaz del repositorio de tipos de contrato 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface ContractTypeRepository extends Repository<ContractType>{
	/**
	 * Método findByName
	 * Busca el tipo de contrato del nombre pasado por parámetro
	 * 
	 * @param name, nombre del tipo de contrato
	 * @return ContractType, tipo de contrato
	 */
	Optional<ContractType> findByName(String name);
}
