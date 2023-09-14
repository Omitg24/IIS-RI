package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListPayrollByMechanicAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ListPayrollByMechanicAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String dni = Console.readString("Mechanic dni  ");

		//Execute logic
		List<PayrollSummaryBLDto> payrolls;
		payrolls = BusinessFactory.forPayrollService().getAllPayrollsForMechanic(dni);
		
		//Print result
		Console.println(String.format("Payrolls for mechanic %s", dni));
		Printer.printPayrollsSummary(payrolls);
	}
}
