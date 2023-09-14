package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

/**
 * Titulo: Clase AddMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class AddMechanicAction implements Action {
	/**
	 * Método execute
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		// Get info
		String dni = Console.readString("Dni");
		String name = Console.readString("Name");
		String surname = Console.readString("Surname");
		
		//Execute logic
		MechanicBLDto mechanic = new MechanicBLDto();
		mechanic.dni = dni;
		mechanic.name = name;
		mechanic.surname = surname;
		BusinessFactory.forMechanicService().addMechanic(mechanic);

		// Print result
		Console.println("Mechanic added");
	}

}
