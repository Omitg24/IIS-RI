package uo.ri.cws.application.service;

import uo.ri.cws.application.ServiceFactory;
import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.contract.ContractService;
import uo.ri.cws.application.service.contract.crud.ContractServiceImpl;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.crud.ContractTypeServiceImpl;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.create.InvoicingServiceImpl;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.crud.MechanicCrudServiceImpl;
import uo.ri.cws.application.service.payroll.PayrollService;
import uo.ri.cws.application.service.payroll.crud.PayrollServiceImpl;
import uo.ri.cws.application.service.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.CloseWorkOrderService;
import uo.ri.cws.application.service.workorder.ViewAssignedWorkOrdersService;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.util.exception.NotYetImplementedException;

/**
 * Título: Clase BusinessFactory
 * Descripción: Contiene la implementación de la factoría de negocio de las 
 * llamadas a las factorías de servicio 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class BusinessFactory implements ServiceFactory {
	/**
	 * Método forMechanicCrudService
	 * Devuelve el servicio de vehículos
	 * 
	 * @return MechanicCrudService
	 */
    @Override
    public MechanicCrudService forMechanicCrudService() {
    	return new MechanicCrudServiceImpl();
    }
    
    /**
	 * Método forCreateInvoiceService
	 * Devuelve el servicio de facturas 
	 * 
	 * @return InvoicingService
	 */
    @Override
    public InvoicingService forCreateInvoiceService() {
    	return new InvoicingServiceImpl();
    }

    /**
	 * Método forVehicleCrudService
	 * Devuelve el servicio de tipos de vehiculo
	 * 
	 * @return VehicleCrudService
	 */
    @Override
    public VehicleCrudService forVehicleCrudService() {
    	throw new NotYetImplementedException("VehicleCrudService not yet implemented");
    }

    /**
	 * Método forClosingBreakdown
	 * Devuelve el servicio de cierre de averías 
	 * 
	 * @return CloseWorkOrderService
	 */
    @Override
    public CloseWorkOrderService forClosingBreakdown() {
    	throw new NotYetImplementedException("CloseWorkOrderService not yet implemented");
    }

    /**
	 * Método forVehicleTypeCrudService
	 * Devuelve el servicio de vehiculos
	 * 
	 * @return VehicleTypeCrudService
	 */
    @Override
    public VehicleTypeCrudService forVehicleTypeCrudService() {
    	throw new NotYetImplementedException("VehicleTypeCrudService not yet implemented");
    }

    /**
	 * Método forSparePartCrudService
	 * Devuelve el servicio de piezas de repuesto 
	 * 
	 * @return SparePartCrudService
	 */
    @Override
    public SparePartCrudService forSparePartCrudService() {
    	throw new NotYetImplementedException("SparePartCrudService not yet implemented");
    }

    /**
	 * Método forClienteCrudService
	 * Devuelve el servicio de clientes 
	 * 
	 * @return ClientCrudService
	 */
    @Override
    public ClientCrudService forClienteCrudService() {
    	throw new NotYetImplementedException("ClientCrudService not yet implemented");
    }

    /**
	 * Método forClientHistoryService
	 * Devuelve el servicio de historial de clientes 
	 * 
	 * @return ClientHistoryService
	 */
    @Override
    public ClientHistoryService forClientHistoryService() {
    	throw new NotYetImplementedException("ClientHistoryService not yet implemented");
    }

    /**
	 * Método forWorkOrderCrudService
	 * Devuelve el servicio de averías 
	 * 
	 * @return WorkOrderCrudService
	 */
    @Override
    public WorkOrderCrudService forWorkOrderCrudService() {
    	throw new NotYetImplementedException("WorkOrderCrudService not yet implemented");
    }

    /**
	 * Método forViewAssignedWorkOrdersService
	 * Devuelve el servicio de ver averías asignadas
	 * 
	 * @return ViewAssignedWorkOrdersService
	 */
    @Override
    public ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService() {
    	throw new NotYetImplementedException("ViewAssignedWorkOrdersService not yet implemented");
    }

    /**
	 * Método forContractService
	 * Devuelve el servicio de contratos 
	 * 
	 * @return ContractService
	 */
    @Override
    public ContractService forContractService() {
    	return new ContractServiceImpl();
    }

	/**
	 * Método forContractTypeService
	 * Devuelve el servicio de tipos de contrato 
	 * 
	 * @return ContractTypeService
	 */
    @Override
    public ContractTypeService forContractTypeService() {
    	return new ContractTypeServiceImpl();
    }

	/**
	 * Método forPayrollService
	 * Devuelve el servicio de nóminas 
	 * 
	 * @return PayrollService
	 */
    @Override
    public PayrollService forPayrollService() {
    	return new PayrollServiceImpl();
    }

	/**
	 * Método forProfessionalGroupService
	 * Devuelve el servicio de grupos profesionales 
	 * 
	 * @return ProfessionalGroupService
	 */
    @Override
    public ProfessionalGroupService forProfessionalGroupService() {
    	throw new NotYetImplementedException("ProfessionalGroupService not yet implemented");
    }
}