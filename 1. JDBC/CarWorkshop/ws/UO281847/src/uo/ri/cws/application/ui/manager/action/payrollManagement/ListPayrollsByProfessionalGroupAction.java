package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListPayrollsByProfessionalGroupAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ListPayrollsByProfessionalGroupAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String name = Console.readString("Professional group  ");

		//Execute logic
		List<PayrollSummaryBLDto> payrolls;
		payrolls = BusinessFactory.forPayrollService().getAllPayrollsForProfessionalGroup(name);

		//Print result
		Console.println(String.format("Payrolls for professional group %s", name));
		Printer.printPayrollsSummary(payrolls);
	}
}
