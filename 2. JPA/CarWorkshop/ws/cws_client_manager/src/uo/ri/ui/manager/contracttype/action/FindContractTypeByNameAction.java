package uo.ri.ui.manager.contracttype.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTI�N DE TIPOS DE CONTRATO - Listado de tipos de contrato por nombre
/**
 * Titulo: Clase FindContractTypeByNameAction
 * Descripci�n: Contiene la acci�n de listar un tipo de contrato de la base de 
 * datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 16 nov 2022
 */
public class FindContractTypeByNameAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data	
		String name = Console.readString("Contract type id ");
		
		// Invoke the service
		ContractTypeService ctService = Factory.service.forContractTypeService();
		Optional<ContractTypeDto> oct = ctService.findContractTypeByName(name);
		
		// Show result		
		if (oct.isEmpty()) {
			Console.println("\nContract type does not exist\n");
		} else {
			ContractTypeDto ct = oct.get();
			Printer.printContractType(ct);
		}
	}

}
