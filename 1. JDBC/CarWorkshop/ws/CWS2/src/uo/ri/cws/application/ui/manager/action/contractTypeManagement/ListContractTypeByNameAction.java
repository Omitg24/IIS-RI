package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import java.util.Optional;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListContractTypeByNameAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class ListContractTypeByNameAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String name = Console.readString("Contract type name ");
		
		//Execute logic
		Optional<ContractTypeBLDto> ct;
		ct = BusinessFactory.forContractTypeService().findContractTypeByName(name);
		
		//Print results
		Console.println(String.format("Details ContractType %s", name));
		if (ct.isEmpty()) {
			Console.println("\nContract type does not exist\n");
		} else {
			Printer.printContractType(ct.get());
		}
	}

}
