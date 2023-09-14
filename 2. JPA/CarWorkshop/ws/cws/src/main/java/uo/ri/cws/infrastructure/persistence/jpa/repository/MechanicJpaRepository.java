package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.ProfessionalGroup;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase MechanicJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * mecánicos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class MechanicJpaRepository extends BaseJpaRepository<Mechanic> implements MechanicRepository {
	/**
	 * Método findByDni
	 * Devuelve el mecánico que tiene el dni pasado por parámetro
	 * 
	 * @param dni, dni del mecánico
	 * @return Mechanic, mecánico
	 */
	@Override
	public Optional<Mechanic> findByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("Mechanic.findByDni", Mechanic.class)
				.setParameter(1, dni)
				.getResultStream()
				.findFirst();
	}

	/**
	 * Método findAllInForce
	 * Devuelve una lista de los mecánicos con contrato en vigor
	 * 
	 * @return list, lista de los mecánicos
	 */
	@Override
	public List<Mechanic> findAllInForce() {
		return Jpa.getManager()
				.createNamedQuery("Mechanic.findAllInForce", Mechanic.class)
				.getResultList();
	}

	/**
	 * Método findInForceInContractType
	 * Devuelve una lista de los mecánicos con contrato en vigor para un 
	 * tipo de contrato
	 * 
	 * @param contractType, tipo de contrato
	 * @return list, lista de los mecánicos
	 */
	@Override
	public List<Mechanic> findInForceInContractType(ContractType contractType) {
		return Jpa.getManager()
				.createNamedQuery("Mechanic.findInForceInContractType", Mechanic.class)
				.setParameter(1, contractType.getId())
				.getResultList();
	}

	/**
	 * Método findAllInProfessionalGroup
	 * Devuelve una lista de los mecánicos para un grupo profesional
	 * 
	 * @param group, grupo profesional
	 * @return list, lista de los mecánicos
	 */
	@Override
	public List<Mechanic> findAllInProfessionalGroup(ProfessionalGroup group) {
		// TODO Auto-generated method stub
		return null;
	}
}
