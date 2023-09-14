package uo.ri.ui.manager.contracttype.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTI�N DE TIPOS DE CONTRATO - Borrar tipos de contrato
/**
 * Titulo: Clase DeleteContractTypeAction
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 18 oct 2022
 */
public class DeleteContractTypeAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String name = Console.readString("Name ");

		// Invoke the service
		ContractTypeService ctService = Factory.service.forContractTypeService();
		ctService.deleteContractType(name);
		
		// Show result
		Console.println("The contract type has been removed");
	}

}
