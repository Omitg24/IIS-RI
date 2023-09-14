package uo.ri.ui.manager.mechanic.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase UpdateMechanicAction
 * Descripci�n: Contiene la acci�n de actualizar un mec�nico de la base de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 15 nov 2022
 */
public class UpdateMechanicAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {		
		// Ask the user for the mechanic id
		String id = Console.readString("Mechanic id"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		MechanicDto m = new MechanicDto();		
		m.id = id;
		m.name = name;
		m.surname = surname;
		mService.updateMechanic(m);
		
		// Show result
		Console.println("The mechanic has been updated");
		Printer.printMechanic(m);
	}
}
