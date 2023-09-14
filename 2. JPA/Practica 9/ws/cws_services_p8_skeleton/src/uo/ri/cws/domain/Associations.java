package uo.ri.cws.domain;

/**
 * Titulo: Clase Associations 
 * Descripción: Contiene las distintas asociaciones entre las entidades 
 * que forman el modelo
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
public class Associations {
	/**
	 * Titulo: Clase Own 
	 * Descripción: Relación entre clientes y vehiculos
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Own {
		/**
		 * Método link 
		 * Crea la relacion entre el cliente y el vehiculo
		 * 
		 * @param client,  cliente
		 * @param vehicle, vehiculo
		 */
		public static void link(Client client, Vehicle vehicle) {
			vehicle.setClient(client);
			client._getVehicles().add(vehicle);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre el cliente y el vehiculo
		 * 
		 * @param client,  cliente
		 * @param vehicle, vehiculo
		 */
		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle.setClient(null);
		}
	}

	/**
	 * Titulo: Clase Classify 
	 * Descripción: Relación entre tipos de vehiculo y vehiculos
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Classify {
		/**
		 * Método link 
		 * Crea la relación entre el tipo de vehiculo y el vehiculo
		 * 
		 * @param vehicleType, tipo de vehiculo
		 * @param vehicle,     vehiculo
		 */
		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().add(vehicle);
			vehicle.setType(vehicleType);
		}

		/**
		 * Método unlink 
		 * Anula la relación entre el tipo de vehiculo y el vehiculo
		 * 
		 * @param vehicleType, tipo de vehiculo
		 * @param vehicle,     vehiculo
		 */
		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle.setType(null);
		}
	}

	/**
	 * Titulo: Clase Pay 
	 * Descripción: Relación entre clientes y métodos de pago
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Pay {
		/**
		 * Método link 
		 * Crea la relacion entre el cliente y el método de pago
		 * 
		 * @param client, cliente
		 * @param pm,     método de pago
		 */
		public static void link(Client client, PaymentMean pm) {
			client._getPaymentMeans().add(pm);
			pm._setClient(client);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre el cliente y el método de pago
		 * 
		 * @param client, cliente
		 * @param pm,     método de pago
		 */
		public static void unlink(Client client, PaymentMean pm) {
			client._getPaymentMeans().remove(pm);
			pm._setClient(null);
		}
	}

	/**
	 * Titulo: Clase Fix 
	 * Descripción: Relación entre el vehiculo y la avería
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Fix {
		/**
		 * Método link 
		 * Crea la relacion entre el vehiculo y la avería
		 * 
		 * @param vehicle,   vehiculo
		 * @param workOrder, avería
		 */
		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().add(workOrder);
			workOrder._setVehicle(vehicle);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre el vehiculo y la avería
		 * 
		 * @param vehicle,   vehiculo
		 * @param workOrder, avería
		 */
		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);
		}
	}

	/**
	 * Titulo: Clase ToInvoice 
	 * Descripción: Relación entre la factura y la avería
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class ToInvoice {
		/**
		 * Método link 
		 * Crea la relacion entre la factura y la avería
		 * 
		 * @param invoice,   factura
		 * @param workOrder, avería
		 */
		public static void link(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkOrders().add(workOrder);
			workOrder._setInvoice(invoice);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre la factura y la avería
		 * 
		 * @param invoice,   factura
		 * @param workOrder, avería
		 */
		public static void unlink(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkOrders().remove(workOrder);
			workOrder._setInvoice(null);
		}
	}

	/**
	 * Titulo: Clase ToCharge 
	 * Descripción: Relación entre el método de pago, el cargo y la factura
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class ToCharge {
		/**
		 * Método link 
		 * Crea la relacion entre el método de pago, el cargo y la factura
		 * 
		 * @param pm,      método de pago
		 * @param charge,  cargo
		 * @param invoice, factura
		 */
		public static void link(PaymentMean pm, Charge charge, Invoice invoice) {
			charge.setPaymentMean(pm);
			charge.setInvoice(invoice);
			pm._getCharges().add(charge);
			invoice._getCharges().add(charge);
		}

		/**
		 * Método unlink
		 * Anula la relacion entre el método de pago, el cargo y la factura
		 * 
		 * @param charge, cargo
		 */
		public static void unlink(Charge charge) {
			charge.getPaymentMean()._getCharges().remove(charge);
			charge.getInvoice()._getCharges().remove(charge);
			charge.setPaymentMean(null);
			charge.setInvoice(null);
		}
	}

	/**
	 * 
	 * Titulo: Clase Assign 
	 * Descripción: Relación entre el mecánico y la avería
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Assign {
		/**
		 * Método link 
		 * Crea la relacion entre el mecánico y la avería
		 * 
		 * @param mechanic,  mecánico
		 * @param workOrder, avería
		 */
		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().add(workOrder);
			workOrder._setMechanic(mechanic);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre el mecánico y la avería
		 * 
		 * @param mechanic,  mecánico
		 * @param workOrder, avería
		 */
		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().remove(workOrder);
			workOrder._setMechanic(null);
		}
	}

	/**
	 * Titulo: Clase Intervene 
	 * Descripción: Relación entre la avería, la interveción y el mecánico
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Intervene {
		/**
		 * Método link 
		 * Crea la relacion entre la avería, la intervención y el mecánico
		 * 
		 * @param workOrder,    avería
		 * @param intervention, intervención
		 * @param mechanic,     mecánico
		 */
		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setWorkOrder(workOrder);
			intervention._setMechanic(mechanic);
			workOrder._getInterventions().add(intervention);
			mechanic._getInterventions().add(intervention);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre la avería, la interveción y el mecánico
		 * 
		 * @param intervention, intervención
		 */
		public static void unlink(Intervention intervention) {
			intervention.getMechanic()._getInterventions().remove(intervention);
			intervention.getWorkOrder()._getInterventions().remove(intervention);
			intervention._setMechanic(null);
			intervention._setWorkOrder(null);
		}
	}

	/**
	 * Titulo: Clase Substitute 
	 * Descripción: Relación entre la pieza de repuesto, la sustitución 
	 * y la interveción
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 12 nov 2022
	 */
	public static class Substitute {
		/**
		 * Método link 
		 * Crea la relacion entre la pieza de repuesto, la sustitución y la
		 * interveción
		 * 
		 * @param spare,        pieza de repuesto
		 * @param sustitution,  sustitución
		 * @param intervention, interveción
		 */
		public static void link(SparePart spare, Substitution sustitution, Intervention intervention) {
			sustitution._setSparePart(spare);
			sustitution._setIntervention(intervention);
			spare._getSubstitutions().add(sustitution);
			intervention._getSubstitutions().add(sustitution);
		}

		/**
		 * Método unlink 
		 * Anula la relacion entre la pieza de repuesto, la sustitución y
		 * la intervención
		 * 
		 * @param sustitution, sustitución
		 */
		public static void unlink(Substitution sustitution) {
			sustitution.getSparePart()._getSubstitutions().remove(sustitution);
			sustitution.getIntervention()._getSubstitutions().remove(sustitution);
			sustitution._setSparePart(null);
			sustitution._setIntervention(null);
		}
	}
}
