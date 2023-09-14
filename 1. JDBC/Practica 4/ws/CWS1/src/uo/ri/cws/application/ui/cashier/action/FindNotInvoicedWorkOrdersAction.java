package uo.ri.cws.application.ui.cashier.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindNotInvoicedWorkOrdersAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindNotInvoicedWorkOrdersAction implements Action {
	/**
	 * Método execute
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String dni = Console.readString("Client DNI ");
		
		//Execute logic
		List<WorkOrderForInvoicingBLDto> workOrders = 
				BusinessFactory.forInvoicingService().findNotInvoicedWorkOrdersByClientDni(dni);
		
		//Print results
		Console.println("\nClient's not invoiced work orders\n");			
		Printer.printInvoicingWorkOrders(workOrders);
	}
}