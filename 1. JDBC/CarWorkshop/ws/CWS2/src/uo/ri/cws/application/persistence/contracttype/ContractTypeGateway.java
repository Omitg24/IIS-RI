package uo.ri.cws.application.persistence.contracttype;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

/**
 * Titulo: Interfaz ContractTypeGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public interface ContractTypeGateway extends Gateway<ContractTypeDALDto> {
	/**
	 * Método findByName
	 * @param name
	 * @return ContractTypeDALDto
	 */
	Optional<ContractTypeDALDto> findByName(String name);
	
	/**
	 * Titulo: Clase ContractTypeDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 18 oct 2022
	 */
	public class ContractTypeDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo name
		 */
		public String name;
		/**
		 * Atributo compensationDays
		 */
		public double compensationDays;
	}
}
