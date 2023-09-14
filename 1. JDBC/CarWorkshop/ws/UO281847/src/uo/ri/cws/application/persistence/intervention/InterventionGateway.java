package uo.ri.cws.application.persistence.intervention;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

/**
 * Titulo: Interfaz InterventionGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 24 oct 2022
 */
public interface InterventionGateway extends Gateway<InterventionDALDto> {
	/**
	 * Método findByMechanic
	 * @param id
	 * @return list
	 */
	List<InterventionDALDto> findByMechanic(String id);
	
	/**
	 * Titulo: Clase InterventionDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 24 oct 2022
	 */
	public class InterventionDALDto {
		/**
		 * Atributo id 
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo date
		 */
		public LocalDate date;
		/**
		 * Atributo minutes
		 */
		public int minutes;
		/**
		 * Atributo mechanicId
		 */
		public String mechanicId;
		/**
		 * Atributo workOrderId
		 */
		public String workOrderId;
	}
}
