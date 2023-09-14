package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListAllContractTypeAction
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 19 oct 2022
 */
public class ListAllContractTypeAction implements Action {
	/**
	 * M�todo execute
	 * 
	 * @throws BusinessException	
	 */
	@Override
	public void execute() throws BusinessException {
		//Execute logic
		List<ContractTypeBLDto> contractTypes = BusinessFactory.forContractTypeService().findAllContractTypes();
		
		//Print result
		Console.println("\nList of contract types \n");
		Printer.printContractTypes(contractTypes);
	}
}
