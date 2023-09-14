package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

/**
 * Titulo: Clase DeleteMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class DeleteMechanicAction implements Action {
	/**
	 * Método execute 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String idMechanic = Console.readString("Type mechanic id ");
		
		//Execute logic
		BusinessFactory.forMechanicService().deleteMechanic(idMechanic);
		
		//Print result
		Console.println("Mechanic deleted");
	}

}
