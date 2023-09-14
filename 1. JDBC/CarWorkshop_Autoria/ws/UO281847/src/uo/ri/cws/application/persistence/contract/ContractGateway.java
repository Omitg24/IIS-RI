package uo.ri.cws.application.persistence.contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

/**
 * Titulo: Interfaz ContractGateway
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 19 oct 2022
 */
public interface ContractGateway extends Gateway<ContractDALDto> {
	/**
	 * M�todo findByContractTypeName
	 * @param id
	 * @return ContractDALDto
	 */
	Optional<ContractDALDto> findByContractTypeId(String id);
	
	/**
	 * M�todo findByContractTypeName
	 * @param id
	 * @return list
	 */
	List<ContractDALDto> findByContractTypeIdInForce(String id);
	
	/**
	 * M�todo findByMechanicId
	 * @param id
	 * @return list
	 */
	List<ContractDALDto> findByMechanic(String id);
	
	/**
	 * M�todo findByMechanicIdInForce
	 * @param id
	 * @return ContractDALDto
	 */
	Optional<ContractDALDto> findByMechanicInForce(String id);
	
	/**
	 * M�todo findByProfessionalGroup
	 * @param id
	 * @return list
	 */
	List<ContractDALDto> findByProfessionalGroup(String id);
	
	/**
	 * M�todo findInForce
	 * @return list
	 */
	List<ContractDALDto> findInForce();
	
	/**
	 * M�todo findInForceOrEndingThisMonth
	 * @return list
	 */
	List<ContractDALDto> findInForceOrEndingThisMonth(int month, int year);
	
	/**
	 * Titulo: Clase ContractDALDto
	 *
	 * @author Omar Teixeira Gonz�lez, UO281847
	 * @version 19 oct 2022
	 */
	public class ContractDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public long version;
		/**
		 * Atributo dni
		 */
		public String dni;	//EL ID
		/**
		 * Atributo contractTypeName
		 */
		public String contractTypeName;	//EL ID
		/**
		 * Atributo professionalGroupName
		 */
		public String professionalGroupName;
		/**
		 * Atributo startDate
		 */
		public LocalDate startDate;
		/**
		 * Atributo endDate
		 */
		public LocalDate endDate;
		/**
		 * Atributo annualBaseWage
		 */
		public double annualBaseWage;
		// Filled in reading operations
		/**
		 * Atributo settlement
		 */
		public double settlement;
		/**
		 * Atributo state
		 */
		public String state;
	}
}
