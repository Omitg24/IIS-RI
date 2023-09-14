package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * T�tulo: Clase FindAllPayrollsByProfessionalGroupAction
 * Descripci�n: Contiene la acci�n de listar las n�minas para un grupo profesional 
 * de la base de datos 
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 17 nov 2022
 */
public class FindAllPayrollsByProfessionalGroupAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String name = Console.readString("Professional group  ");

		// Invoke the service
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService().getAllPayrollsForProfessionalGroup(name);

		// Show result
		Console.println(String.format("Payrolls for professional group %s", name));
		Printer.printPayrollsSummary(payrolls);
	}
}
