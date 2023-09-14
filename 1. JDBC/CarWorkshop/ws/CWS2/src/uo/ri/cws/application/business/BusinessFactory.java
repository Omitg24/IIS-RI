package uo.ri.cws.application.business;

import uo.ri.cws.application.business.client.ClientService;
import uo.ri.cws.application.business.client.crud.ClientServiceImpl;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.crud.ContractServiceImpl;
import uo.ri.cws.application.business.contracttype.ContractTypeService;
import uo.ri.cws.application.business.contracttype.crud.ContractTypeServiceImpl;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.InvoicingServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.MechanicServiceImpl;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.crud.PayrollServiceImpl;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.business.professionalgroup.crud.ProfessionalGroupServiceImpl;

/**
 * Titulo: Clase BusinessFactory
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class BusinessFactory {
	/**
	 * Método forMechanicService 
	 * Devuelve la implementación de los mecánicos
	 * 
	 * @return MechanicServiceImpl
	 */
	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	/**
	 * Método forInvoicingService 
	 * Devuelve la implementación de las facturas
	 * 
	 * @return InvoicingServiceImpl
	 */
	public static InvoicingService forInvoicingService() {
		return new InvoicingServiceImpl();
	}

	/**
	 * Método forContractService 
	 * Devuelve la implementación de los contratos
	 * 
	 * @return ContractServiceImpl
	 */
	public static ContractService forContractService() {
		return new ContractServiceImpl();
	}
	
	//GESTIÓN DE TIPOS DE CONTRATO
	/**
	 * Método forContractTypeService
	 * @return ContractTypeServiceImpl
	 */
	public static ContractTypeService forContractTypeService() {
		return new ContractTypeServiceImpl();
	}

	/**
	 * Método forPayrollService 
	 * Devuelve la implementación de las nóminas
	 * 
	 * @return PayrollServiceImpl
	 */
	public static PayrollService forPayrollService() {
		return new PayrollServiceImpl();
	}

	/**
	 * Método forClientService
	 * Devuelve la implementación de los clientes
	 * 
	 * @return ClientServiceImpl
	 */
	public static ClientService forClientService() {
		return new ClientServiceImpl();
	}

	/**
	 * Método forProfessionalGroupService
	 * Devuelve la implementación de los grupos de profesionales
	 * 
	 * @return ProfessionalGroupServiceImpl
	 */
	public static ProfessionalGroupService forProfessionalGroupService() {
		return new ProfessionalGroupServiceImpl();
	}
}
