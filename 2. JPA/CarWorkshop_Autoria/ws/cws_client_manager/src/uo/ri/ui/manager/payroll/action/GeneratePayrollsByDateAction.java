package uo.ri.ui.manager.payroll.action;

import java.time.LocalDate;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase GeneratePayrollsByDateAction
 * Descripci�n: Contiene la acci�n de generar la n�mina por fecha
 *  a los contratos 
 * actuales de la base de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class GeneratePayrollsByDateAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		LocalDate date = Console.readDate("Date ");
		
		// Invoke the service		
		Factory.service.forPayrollService().generatePayrolls(date);
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService()
				.getAllPayrolls();
		
		// Show result
		Console.printf("Generated %d new payrolls", payrolls.size());
		Printer.printPayrollsSummary(payrolls);
	}
}
