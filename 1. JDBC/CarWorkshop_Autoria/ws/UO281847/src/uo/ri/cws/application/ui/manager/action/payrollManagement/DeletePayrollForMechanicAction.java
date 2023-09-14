package uo.ri.cws.application.ui.manager.action.payrollManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

/**
 * Titulo: Clase DeletePayrollForMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class DeletePayrollForMechanicAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		// Get info
		String dni = Console.readString("Mechanic dni ");

		//Execute logic
		BusinessFactory.forPayrollService().deleteLastPayrollFor(dni);
		
		// Print result
		Console.println(String.format("Last payroll for mechanic %s successfully deleted", dni));
	}
}
