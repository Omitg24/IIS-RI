package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase GeneratePayrollsAction
 * Descripción: Contiene la acción de generar la nómina a los contratos 
 * actuales de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class GeneratePayrollsAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
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
