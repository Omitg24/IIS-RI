package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase DeleteLastPayrollsAction
 * Descripci�n: Contiene la acci�n de borrar las �ltimas n�minas de la base de 
 * datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class DeleteLastPayrollsAction implements Action {
	/**
	 * M�todo execue
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		Factory.service.forPayrollService().deleteLastPayrolls();
		
		// Show results
		Console.println("Last payroll successfully deleted");
	}

}
