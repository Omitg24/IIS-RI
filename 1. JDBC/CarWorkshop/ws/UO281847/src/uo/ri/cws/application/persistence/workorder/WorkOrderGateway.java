package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

/**
 * Titulo: Interfaz WorkOrderGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public interface WorkOrderGateway extends Gateway<WorkOrderDALDto> {
	/**
	 * Método findByMechanic
	 * @param id
	 * @return list
	 */
	List<WorkOrderDALDto> findByMechanic(String id);
	
	/**
	 * Método findNotInvoicedForVehicles
	 * @param vehicleIds
	 * @return list
	 */
	List<WorkOrderDALDto> findNotInvoicedForVehicles(List<String> vehicleIds);

	/**
	 * Método findVehicleId
	 * @param vehicleId
	 * @return list
	 */
	List<WorkOrderDALDto> findByVehicleId(String vehicleId);
	
	/**
	 * Método findByIds 
	 * @param ids
	 * @return list
	 */
	List<WorkOrderDALDto> findByIds(List<String> ids);

	/**
	 * Método findByInvoice
	 * @param id
	 * @return list
	 */
	List<WorkOrderDALDto> findByInvoice(String id);

	/**
	 * Método findInvoiced
	 * @return list
	 */
	List<WorkOrderDALDto> findInvoiced();

	/**
	 * Titulo: Clase WorkOrderDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 18 oct 2022
	 */
	public class WorkOrderDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo amount
		 */
		public Double amount;
		/**
		 * Atributo date
		 */
		public LocalDateTime date;
		/**
		 * Atributo description
		 */
		public String description;
		/**
		 * Atributo state
		 */
		public String state;
		/**
		 * Atributo invoice_id
		 */
		public String invoice_id;
		/**
		 * Atributo mechanic_id
		 */
		public String mechanic_id;
		/**
		 * Atributo vehicle_id
		 */
		public String vehicle_id;
		/**
		 * Atributo usedforvoucher
		 */
		public Boolean usedforvoucher;
	}

}
