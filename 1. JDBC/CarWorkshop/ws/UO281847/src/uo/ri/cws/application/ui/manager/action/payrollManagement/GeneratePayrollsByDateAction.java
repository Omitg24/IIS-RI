package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.time.LocalDate;
import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase GeneratePayrollsByDateAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class GeneratePayrollsByDateAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		LocalDate date =  LocalDate.parse(Console.readString("Date  "));
		
		//Execute logic
		List<PayrollSummaryBLDto> payrolls;
		BusinessFactory.forPayrollService().generatePayrolls(date);
		payrolls = BusinessFactory.forPayrollService().getAllPayrolls();
		
		//Print results
		Console.printf("Generated %d new payrolls", payrolls.size());
		Printer.printPayrollsSummary(payrolls);
	}
}
