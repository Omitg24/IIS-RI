package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase GeneratePayrollsAction
 * Descripci�n: Contiene la acci�n de generar la n�mina a los contratos 
 * actuales de la base de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class GeneratePayrollsAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Invoke the service		
		Factory.service.forPayrollService().generatePayrolls();
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService().getAllPayrolls();
		
		// Show result
		Console.printf("Generated %d new payrolls", payrolls.size());
		Printer.printPayrollsSummary(payrolls);
	}
}
