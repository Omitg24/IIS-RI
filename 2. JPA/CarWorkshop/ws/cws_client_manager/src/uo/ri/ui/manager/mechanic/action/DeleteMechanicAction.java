package uo.ri.ui.manager.mechanic.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase DeleteMechanicAction
 * Descripci�n: Contiene la acci�n de borrar un mec�nico de la base de datos  
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 15 nov 2022
 */
public class DeleteMechanicAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String idMecanico = Console.readString("Mechanic id "); 
		
		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		mService.deleteMechanic(idMecanico);
		
		// Show result
		Console.println("The mechanic has been removed");
	}
}
