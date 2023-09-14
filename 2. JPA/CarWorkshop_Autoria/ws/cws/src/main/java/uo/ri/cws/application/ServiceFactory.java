package uo.ri.cws.application;

import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.contract.ContractService;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.payroll.PayrollService;
import uo.ri.cws.application.service.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.CloseWorkOrderService;
import uo.ri.cws.application.service.workorder.ViewAssignedWorkOrdersService;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;

/**
 * Título: Clase ServiceFactory
 * Descripción: Contiene las factorias de servicio 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public interface ServiceFactory {

    // Manager use cases
	/**
	 * Método forMechanicCrudService
	 * Devuelve el servicio de vehículos
	 * 
	 * @return MechanicCrudService
	 */
    MechanicCrudService forMechanicCrudService();
	/**
	 * Método forVehicleTypeCrudService
	 * Devuelve el servicio de vehiculos
	 * 
	 * @return VehicleTypeCrudService
	 */
    VehicleTypeCrudService forVehicleTypeCrudService();
	/**
	 * Método forSparePartCrudService
	 * Devuelve el servicio de piezas de repuesto 
	 * 
	 * @return SparePartCrudService
	 */
    SparePartCrudService forSparePartCrudService();

    // Cash use cases
	/**
	 * Método forCreateInvoiceService
	 * Devuelve el servicio de facturas 
	 * 
	 * @return InvoicingService
	 */
    InvoicingService forCreateInvoiceService();

    // Foreman use cases
	/**
	 * Método forVehicleCrudService
	 * Devuelve el servicio de tipos de vehiculo
	 * 
	 * @return VehicleCrudService
	 */
    VehicleCrudService forVehicleCrudService();
	/**
	 * Método forClienteCrudService
	 * Devuelve el servicio de clientes 
	 * 
	 * @return ClientCrudService
	 */
    ClientCrudService forClienteCrudService();
	/**
	 * Método forClientHistoryService
	 * Devuelve el servicio de historial de clientes 
	 * 
	 * @return ClientHistoryService
	 */
    ClientHistoryService forClientHistoryService();
	/**
	 * Método forWorkOrderCrudService
	 * Devuelve el servicio de averías 
	 * 
	 * @return WorkOrderCrudService
	 */
    WorkOrderCrudService forWorkOrderCrudService();

    // Mechanic use cases
	/**
	 * Método forClosingBreakdown
	 * Devuelve el servicio de cierre de averías 
	 * 
	 * @return CloseWorkOrderService
	 */
    CloseWorkOrderService forClosingBreakdown();
	/**
	 * Método forViewAssignedWorkOrdersService
	 * Devuelve el servicio de ver averías asignadas
	 * 
	 * @return ViewAssignedWorkOrdersService
	 */
    ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService();

    // Contracts and payrolls
	/**
	 * Método forContractService
	 * Devuelve el servicio de contratos 
	 * 
	 * @return ContractService
	 */
    ContractService forContractService();
	/**
	 * Método forContractTypeService
	 * Devuelve el servicio de tipos de contrato 
	 * 
	 * @return ContractTypeService
	 */
    ContractTypeService forContractTypeService();
	/**
	 * Método forPayrollService
	 * Devuelve el servicio de nóminas 
	 * 
	 * @return PayrollService
	 */
    PayrollService forPayrollService();
	/**
	 * Método forProfessionalGroupService
	 * Devuelve el servicio de grupos profesionales 
	 * 
	 * @return ProfessionalGroupService
	 */
    ProfessionalGroupService forProfessionalGroupService();
}