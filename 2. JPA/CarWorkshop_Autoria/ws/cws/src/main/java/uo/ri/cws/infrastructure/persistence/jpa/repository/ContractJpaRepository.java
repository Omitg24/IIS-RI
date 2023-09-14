package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.repository.ContractRepository;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase ContractJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * contratos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class ContractJpaRepository extends BaseJpaRepository<Contract> implements ContractRepository{
	/**
     * Método findAllInForce
     * Devuelve una lista de contratos en vigor 
     * 
     * @return list, lista de contratos 
     */
	@Override
	public List<Contract> findAllInForce() {
		return Jpa.getManager()
				.createNamedQuery("Contract.findAllInForce", Contract.class)
				.getResultList();
	}

	/**
     * Método findByMechanicId
     * Devuelve una lista de contratos del id del mecánico pasado por parámetro
     * 
     * @param id, id del mecánico
     * @return list, lista de contratos
     */
	@Override
	public List<Contract> findByMechanicId(String id) {
		return Jpa.getManager()
				.createNamedQuery("Contract.findByMechanicId", Contract.class)
				.setParameter(1, id)
				.getResultList();
	}

	/**
	 * Método findByProfessionalGroupId
	 * Devuelve una lista de contratos del id del grupo profesional pasado por 
	 * parámetro
	 * 
	 * @param id, id del grupo profesional
	 * @return list, lista de contratos
	 */
	@Override
	public List<Contract> findByProfessionalGroupId(String id) {
		return Jpa.getManager()
				.createNamedQuery("Contract.findByProfessionalGroupId", Contract.class)
				.setParameter(1, id)
				.getResultList();
	}

	/**
	 * Método findByContractTypeId
	 * Devuelve una lista de contratos del id del tipo de contrato pasado por
	 * parámetro
	 * 
	 * @param id, id del tipo de contrato
	 * @return list, lista de contratos
	 */
	@Override
	public List<Contract> findByContractTypeId(String id) {
		return Jpa.getManager()
				.createNamedQuery("Contract.findByContractTypeId", Contract.class)
				.setParameter(1, id)
				.getResultList();
	}

	/**
	 * Método findAllInForceThisMonth
	 * Devuelve una lista de contratos con el mes de la fecha pasado por 
	 * parámetro
	 * 
	 * @param present, fecha
	 * @return list, lista de contratos
	 */
	@Override
	public List<Contract> findAllInForceThisMonth(LocalDate present) {
		return Jpa.getManager()
				.createNamedQuery("Contract.findAllInForceThisMonth", Contract.class)
				.setParameter(1, present.getMonthValue())
				.setParameter(2, present.getYear())
				.getResultList();
	}

}
