package uo.ri.ui.manager.payroll.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase FindPayrollDetailsAction
 * Descripci�n: Contiene la acci�n de listar una n�mina de la base de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class FindPayrollDetailsAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String id = Console.readString("Payroll id  ");

		// Invoke the service
		Optional<PayrollBLDto> payroll = Factory.service.forPayrollService()
				.getPayrollDetails(id);
		
		// Show result
		Console.println(String.format("Details Payroll %s", id));
		if (payroll.isEmpty()) {
			Console.println("\nPayroll does not exist\n");
		} else {
			Printer.printPayroll(payroll.get());
		}		
	}
}
