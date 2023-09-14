package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase DeleteLastPayrollForMechanicAction
 * Descripci�n: Contiene la acci�n de borrar una n�mina para un mec�nico de la 
 * base de datos  
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class DeleteLastPayrollForMechanicAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String id = Console.readString("Mechanic id ");

		// Invoke the service
		Factory.service.forPayrollService().deleteLastPayrollFor(id);
		
		// Show result
		Console.println(String.format("Last payroll for mechanic %s successfully deleted", id));
	}
}
