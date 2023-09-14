package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase FindAllPayrollsByMechanicAction
 * Descripción: Contiene la acción de listar las nóminas para un mecánico de la 
 * base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class FindAllPayrollsByMechanicAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String id = Console.readString("Mechanic id  ");

		// Invoke the service
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService()
				.getAllPayrollsForMechanic(id);
		
		// Show result
		Console.println(String.format("Payrolls for mechanic %s", id));
		Printer.printPayrollsSummary(payrolls);
	}
}
