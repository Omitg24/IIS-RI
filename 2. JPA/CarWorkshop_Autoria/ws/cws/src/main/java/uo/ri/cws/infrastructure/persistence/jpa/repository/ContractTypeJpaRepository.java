package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.ContractTypeRepository;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase MechanicJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * tipos de contrato 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class ContractTypeJpaRepository  extends BaseJpaRepository<ContractType> implements ContractTypeRepository{
	/**
	 * Método findByName
	 * Busca el tipo de contrato del nombre pasado por parámetro
	 * 
	 * @param name, nombre del tipo de contrato
	 * @return ContractType, tipo de contrato
	 */
	@Override
	public Optional<ContractType> findByName(String name) {
		return Jpa.getManager()
				.createNamedQuery("ContractType.findByName", ContractType.class)
				.setParameter(1, name)
				.getResultStream()
				.findFirst();
	}
}
