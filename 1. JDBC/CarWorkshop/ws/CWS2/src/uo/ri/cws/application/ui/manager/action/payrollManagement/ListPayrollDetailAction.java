package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.Optional;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListPayrollDetailAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ListPayrollDetailAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String id = Console.readString("Payroll id  ");

		//Execute logic
		Optional<PayrollBLDto> payroll;
		payroll = BusinessFactory.forPayrollService().getPayrollDetails(id);
		
		//Print result
		Console.println(String.format("Details Payroll %s", id));
		if (payroll.isEmpty()) {
			Console.println("\nPayroll does not exist\n");
		} else {
			Printer.printPayroll(payroll.get());
		}		
	}
}
