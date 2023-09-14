package uo.ri.cws.application.business.workorder;

import java.time.LocalDateTime;

/**
 * Titulo: Clase WorkOrderService
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public interface WorkorderService {
	/**
	 * Titulo: Clase WorkOrderBLDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 19 oct 2022
	 */
	public class WorkOrderBLDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public long version;
		/**
		 * Atributo vehicleId
		 */
		public String vehicleId;
		/**
		 * Atributo description
		 */
		public String description;
		/**
		 * Atributo date
		 */
		public LocalDateTime date;
		/**
		 * Atributo total
		 */
		public double total;
		/**
		 * Atributo state
		 */
		public String state;
		/**
		 * Atributo usedForVoucher
		 */
		public boolean usedForVoucher;
		// might be null
		/**
		 * Atributo mechanicId
		 */
		public String mechanicId;
		/**
		 * Atributo invoiceId
		 */
		public String invoiceId;

	}
}
