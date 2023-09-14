package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;

/**
 * Titulo: Clase UpdateContractTypeAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class UpdateContractTypeAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String name = Console.readString("Type contract type name to update ");
		Double compensationDays = Console.readDouble("Compensation days ");

		//Execute logic
		ContractTypeBLDto contractType = new ContractTypeBLDto();
		contractType.compensationDays = compensationDays;
		contractType.name = name;
		BusinessFactory.forContractTypeService().updateContractType(contractType);
		
		//Print result
		Console.println("Contract type succesfully updated");		
	}
}
