package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase AddContractTypeAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class AddContractTypeAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String name = Console.readString("Name ");
		Double compensationDays = Console.readDouble("Compensation days ");

		//Execute logic
		ContractTypeBLDto contractType = new ContractTypeBLDto();
		contractType.compensationDays = compensationDays;
		contractType.name = name;		
		ContractTypeBLDto finalContractType = BusinessFactory.forContractTypeService().addContractType(contractType);
		
		//Print result
		Console.println("New contract type succesfully added");
		Printer.printContractType(finalContractType);
	}
}
