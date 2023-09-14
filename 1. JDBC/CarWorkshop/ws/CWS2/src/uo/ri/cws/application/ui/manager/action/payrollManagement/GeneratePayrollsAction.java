package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase GeneratePayrollsAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class GeneratePayrollsAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Execute logic
		List<PayrollSummaryBLDto> payrolls;
		BusinessFactory.forPayrollService().generatePayrolls();
		payrolls = BusinessFactory.forPayrollService().getAllPayrolls();
		
		//Print results
		Console.printf("Generated %d new payrolls", payrolls.size());
		Printer.printPayrollsSummary(payrolls);
	}
}
