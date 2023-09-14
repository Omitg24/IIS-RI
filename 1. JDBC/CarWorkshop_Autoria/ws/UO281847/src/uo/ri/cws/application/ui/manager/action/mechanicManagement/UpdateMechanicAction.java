package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

/**
 * Titulo: Clase UpdateMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class UpdateMechanicAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		// Get info
		String id = Console.readString("Type mechahic id to update");
		String name = Console.readString("Name");
		String surname = Console.readString("Surname");

		MechanicBLDto mechanic = new MechanicBLDto();
		mechanic.id = id;
		mechanic.name = name;
		mechanic.surname = surname;
		BusinessFactory.forMechanicService().updateMechanic(mechanic);

		// Print result
		Console.println("Mechanic updated");
	}

}
