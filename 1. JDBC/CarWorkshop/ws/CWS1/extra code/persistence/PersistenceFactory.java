package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.impl.ClientGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.impl.VehicleGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {


	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

	public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}
	
	public static ClientGateway forClient() {
		return new ClientGatewayImpl();
	}
	
	public static VehicleGateway forVehicle() {
		return new VehicleGatewayImpl();
	}
	
}

