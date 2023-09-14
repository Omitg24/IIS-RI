package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import java.util.Optional;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class FindMechanicAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		// Get info
		String dni = Console.readString("Mechanic DNI ");

		//Execute logic
		Optional<MechanicBLDto> m;
		m = BusinessFactory.forMechanicService().findMechanicByDni(dni);
		
		//Print results
		if (m.isEmpty()) {
			Console.println("\nMechanic does not exist\n");
		} else {
			Printer.printMechanic(m.get());
		}
	}
}
