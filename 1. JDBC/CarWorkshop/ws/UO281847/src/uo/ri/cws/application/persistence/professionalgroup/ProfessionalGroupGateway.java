package uo.ri.cws.application.persistence.professionalgroup;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;

/**
 * Titulo: Clase ProfessionalGroupGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public interface ProfessionalGroupGateway extends Gateway<ProfessionalGroupDALDto> {
	/**
	 * Método findByName
	 * @param name
	 * @return ProfessionalGroupDALDto
	 */
	Optional<ProfessionalGroupDALDto> findByName(String name);
	
	/**
	 * Titulo: Clase ProfessionalGroupBLDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 19 oct 2022
	 */
	public class ProfessionalGroupDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public long version;
		/**
		 * Atributo name
		 */
		public String name;
		/**
		 * Atributo trieniumSalary
		 */
		public double trieniumSalary;
		/**
		 * Atributo productivityRate
		 */
		public double productivityRate;
	}
}
