package uo.ri.cws.application.persistence.mechanic;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

/**
 * Titulo: Interfaz MechanicGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public interface MechanicGateway extends Gateway<MechanicDALDto> {
	/**
	 * Método findByDni Finds a row in the table
	 * 
	 * @param dni, record's field
	 * @return dto from that record, probably null
	 */
	Optional<MechanicDALDto> findByDni(String dni);

	/**
	 * Titulo: Clase MechanicDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 14 oct 2022
	 */
	public class MechanicDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo dni
		 */
		public String dni;
		/**
		 * Atributo name
		 */
		public String name;
		/**
		 * Atributo surname
		 */
		public String surname;
	}
}
