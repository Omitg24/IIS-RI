package uo.ri.ui.manager.contracttype.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTI�N DE TIPOS DE CONTRATO - Listado de tipos de contrato
/**
 * Titulo: Clase FindAllContractTypeAction
 * Descripci�n: Contiene la acci�n de listar los tipos de contrato de la base de 
 * datos 
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 16 nov 2022
 */
public class FindAllContractTypeAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
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
