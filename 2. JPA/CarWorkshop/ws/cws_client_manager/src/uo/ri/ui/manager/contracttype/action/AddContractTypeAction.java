package uo.ri.ui.manager.contracttype.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTIÓN DE TIPOS DE CONTRATO - Añadir tipos de contrato
/**
 * Titulo: Clase AddContractTypeAction
 * Descripción: Contiene la acción de añadir un tipo de contrato a la base de 
 * datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class AddContractTypeAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		ContractTypeDto ct = new ContractTypeDto();	
		ct.name = Console.readString("Name ");
		ct.compensationDays = Console.readDouble("Compensation days ");

		// Invoke the service
		ContractTypeService ctService = Factory.service.forContractTypeService();
		ct = ctService.addContractType(ct);
		
		// Show result
		Console.println("New contract type succesfully added: " + ct.id);
		Printer.printContractType(ct);
	}
}
