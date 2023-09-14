package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.impl.ClientGatewayImpl;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.impl.ContractGatewayImpl;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.impl.ContractTypeGatewayImpl;
import uo.ri.cws.application.persistence.intervention.InterventionGateway;
import uo.ri.cws.application.persistence.intervention.impl.InterventionGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.payroll.impl.PayrollGatewayImpl;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.impl.ProfessionalGroupGatewayImpl;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.impl.VehicleGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

/**
 * Titulo: Clase PersistenceFactory
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class PersistenceFactory {
	/**
	 * Método forMechanic
	 * 
	 * @return MechanicGatewayImpl
	 */
	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

	/**
	 * Método forInvoice
	 * 
	 * @return InvoiceGatewayImpl
	 */
	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}

	/**
	 * Método forWorkOrder
	 * 
	 * @return WorkOrderGatewayImpl
	 */
	public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

	/**
	 * Método forClient
	 * 
	 * @return ClientGatewayImpl
	 */
	public static ClientGateway forClient() {
		return new ClientGatewayImpl();
	}
	
	/**
	 * Método forContractType
	 * 
	 * @return ClientGatewayImpl
	 */
	public static ContractTypeGateway forContractType() {
		return new ContractTypeGatewayImpl();
	}
	
	/**
	 * Método forContract
	 * 
	 * @return ClientGatewayImpl
	 */
	public static ContractGateway forContract() {
		return new ContractGatewayImpl();
	}
	
	/**
	 * Método forIntervention
	 * 
	 * @return InterventionGatewayImpl
	 */
	public static InterventionGateway forIntervention() {
		return new InterventionGatewayImpl();
	}
	
	/**
	 * Método forPayroll
	 * 
	 * @return PayrollGatewayImpl
	 */
	public static PayrollGateway forPayroll() {
		return new PayrollGatewayImpl();
	}
	
	/**
	 * Método forProfessionalGroup
	 * 
	 * @return ProfessionalGroupGateway
	 */
	public static ProfessionalGroupGateway forProfessionalGroup() {
		return new ProfessionalGroupGatewayImpl();
	}
	
	/**
	 * Método forVehicle
	 * 
	 * @return VehicleGatewayImpl
	 */
	public static VehicleGateway forVehicle() {
		return new VehicleGatewayImpl();
	}	
}
