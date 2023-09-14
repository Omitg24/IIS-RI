package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;

/**
 * Titulo: Clase PersistenceFactory
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class PersistenceFactory {
	/**
	 * Método forMechanic
	 * @return MechanicGatewayImpl
	 */
	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

//	public static WorkOrderGateway forWorkOrder() {
//		return new WorkOrderGatewayImpl();
//	}
//
//	public static InvoiceGateway forInvoice() {
//		return new InvoiceGatewayImpl();
//	}
//	
//	public static ClientGateway forClient() {
//		return new ClientGatewayImpl();
//	}
//	
//	public static VehicleGateway forVehicle() {
//		return new VehicleGatewayImpl();
//	}
//	
}

