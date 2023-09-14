package uo.ri.cws.application.repository;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.domain.Contract;

/**
 * Título: Clase ContractRepository
 * Descripción: Contiene a la interfaz del repositorio de contratos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface ContractRepository extends Repository<Contract> {    
    /**
     * Método findAllInForce
     * Devuelve una lista de contratos en vigor 
     * 
     * @return list, lista de contratos 
     */
    List<Contract> findAllInForce();

    /**
     * Método findByMechanicId
     * Devuelve una lista de contratos del id del mecánico pasado por parámetro
     * 
     * @param id, id del mecánico
     * @return list, lista de contratos
     */
	List<Contract> findByMechanicId(String id);

	/**
	 * Método findByProfessionalGroupId
	 * Devuelve una lista de contratos del id del grupo profesional pasado por 
	 * parámetro
	 * 
	 * @param id, id del grupo profesional
	 * @return list, lista de contratos
	 */
	List<Contract> findByProfessionalGroupId(String id);
	
	/**
	 * Método findByContractTypeId
	 * Devuelve una lista de contratos del id del tipo de contrato pasado por
	 * parámetro
	 * 
	 * @param id, id del tipo de contrato
	 * @return list, lista de contratos
	 */
	List<Contract> findByContractTypeId(String id);


	/**
	 * Método findAllInForceThisMonth
	 * Devuelve una lista de contratos con el mes de la fecha pasado por 
	 * parámetro
	 * 
	 * @param present, fecha
	 * @return list, lista de contratos
	 */
	List<Contract> findAllInForceThisMonth(LocalDate present);

}
