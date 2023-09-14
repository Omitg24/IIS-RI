package uo.ri.cws.application.ui.manager.action.payrollManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

/**
 * Titulo: Clase DeleteLastPayrollAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class DeleteLastPayrollAction implements Action {
	/**
	 * Método execue
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Execute logic
		BusinessFactory.forPayrollService().deleteLastPayrolls();
		
		//Print result
		Console.println("Last payroll successfully deleted");
	}

}
