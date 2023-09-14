package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.ProfessionalGroup;

/**
 * Título: Clase MechanicRepository
 * Descripción: Contiene a la interfaz del repositorio de mecánicos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface MechanicRepository extends Repository<Mechanic> {
	/**
	 * Método findByDni
	 * Devuelve el mecánico que tiene el dni pasado por parámetro
	 * 
	 * @param dni, dni del mecánico
	 * @return Mechanic, mecánico
	 */
	Optional<Mechanic> findByDni(String dni);

	/**
	 * Método findAll
	 * Devuelve una lista de los mecánicos
	 * 
	 * @return list, lista de los mecánicos
	 */
	List<Mechanic> findAll();

	/**
	 * Método findAllInForce
	 * Devuelve una lista de los mecánicos con contrato en vigor
	 * 
	 * @return list, lista de los mecánicos
	 */
	List<Mechanic> findAllInForce();

	/**
	 * Método findInForceInContractType
	 * Devuelve una lista de los mecánicos con contrato en vigor para un 
	 * tipo de contrato
	 * 
	 * @param contractType, tipo de contrato
	 * @return list, lista de los mecánicos
	 */
	List<Mechanic> findInForceInContractType(ContractType contractType);

	/**
	 * Método findAllInProfessionalGroup
	 * Devuelve una lista de los mecánicos para un grupo profesional
	 * 
	 * @param group, grupo profesional
	 * @return list, lista de los mecánicos
	 */
	List<Mechanic> findAllInProfessionalGroup(ProfessionalGroup group);
	
	List<Mechanic> findMechanicWithExpensiveRepairs();
}
