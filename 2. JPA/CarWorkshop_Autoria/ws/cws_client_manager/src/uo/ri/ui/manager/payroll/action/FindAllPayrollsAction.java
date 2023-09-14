package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase FindAllPayrollsAction
 * Descripci�n: Contiene la acci�n de listar las n�minas de la base de datos 
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class FindAllPayrollsAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws Exception {
		// Ask the user for data
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService()
				.getAllPayrolls();
		
		// Show result
		Printer.printPayrollsSummary(payrolls);
	}
}
