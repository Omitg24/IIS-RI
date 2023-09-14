package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase DeleteLastPayrollsAction
 * Descripción: Contiene la acción de borrar las últimas nóminas de la base de 
 * datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class DeleteLastPayrollsAction implements Action {
	/**
	 * Método execue
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		Factory.service.forPayrollService().deleteLastPayrolls();
		
		// Show results
		Console.println("Last payroll successfully deleted");
	}

}
