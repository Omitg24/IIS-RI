package uo.ri.ui.manager.mechanic.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase DeleteMechanicAction
 * Descripción: Contiene la acción de borrar un mecánico de la base de datos  
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class DeleteMechanicAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
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
