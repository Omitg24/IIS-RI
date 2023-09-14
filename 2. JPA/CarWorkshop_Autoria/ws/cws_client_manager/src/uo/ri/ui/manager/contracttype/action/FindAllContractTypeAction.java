package uo.ri.ui.manager.contracttype.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTIÓN DE TIPOS DE CONTRATO - Listado de tipos de contrato
/**
 * Titulo: Clase FindAllContractTypeAction
 * Descripción: Contiene la acción de listar los tipos de contrato de la base de 
 * datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllContractTypeAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Invoke the service
		ContractTypeService ctService = Factory.service.forContractTypeService();
		List<ContractTypeDto> contractTypes = ctService.findAllContractTypes();
		
		// Show result
		Console.println("\nList of contract types \n");
		Printer.printContractTypes(contractTypes);
	}
}
