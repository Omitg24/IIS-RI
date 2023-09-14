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
 * T�tulo: Clase FindMechanicByIdAction
 * Descripci�n: Contiene la acci�n de listar un mec�nico de la base de datos 
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 15 nov 2022
 */
public class FindMechanicByIdAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
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
			Console.println("\nEl mec�nico no existe\n");
		} else {
			MechanicDto m = om.get();
			Printer.printMechanic(m);
		}
	}
}
