package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.List;

import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase ListAllSummaryPayroll
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ListAllPayrollsSummariesAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws Exception {
		//Execute logic
		List<PayrollSummaryBLDto> payrolls;
		payrolls = BusinessFactory.forPayrollService().getAllPayrolls();
		
		//Print results
		Printer.printPayrollsSummary(payrolls);
	}
}
