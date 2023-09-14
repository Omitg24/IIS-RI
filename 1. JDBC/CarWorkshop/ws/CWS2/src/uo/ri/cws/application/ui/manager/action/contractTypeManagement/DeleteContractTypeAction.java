package uo.ri.cws.application.ui.manager.action.contractTypeManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

/**
 * Titulo: Clase DeleteContractTypeAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class DeleteContractTypeAction implements Action {
	/**
	 * Método execute
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String name = Console.readString("Name ");

		//Execute logic
		BusinessFactory.forContractTypeService().deleteContractType(name);
		
		//Print result
		Console.println("Contract type succesfully deleted");
	}

}
