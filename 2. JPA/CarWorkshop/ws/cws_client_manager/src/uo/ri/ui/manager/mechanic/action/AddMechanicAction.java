package uo.ri.ui.manager.mechanic.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase AddMechanicAction
 * Descripción: Contiene la acción de añadir un mecánico a la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class AddMechanicAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		MechanicDto m = new MechanicDto();
		m.dni = Console.readString("Dni");
		m.name = Console.readString("Name");
		m.surname = Console.readString("Surname");

		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		m = mService.addMechanic( m );

		// Show result
		Console.println("New mechanic added: " + m.id);
		Printer.printMechanic(m);
	}
}
