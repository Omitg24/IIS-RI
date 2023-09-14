package uo.ri.ui.manager.mechanic.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase FindMechanicByIdAction
 * Descripción: Contiene la acción de listar un mecánico de la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class FindMechanicByIdAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data		
		String mechanicId = Console.readString("Mechanic id ");

		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		Optional<MechanicDto> om = mService.findMechanicById(mechanicId);
		
		// Show result
		if (om.isEmpty()) {
			Console.println("\nEl mecánico no existe\n");
		} else {
			MechanicDto m = om.get();
			Printer.printMechanic(m);
		}
	}
}
