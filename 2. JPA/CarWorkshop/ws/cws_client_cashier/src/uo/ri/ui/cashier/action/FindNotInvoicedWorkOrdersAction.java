package uo.ri.ui.cashier.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoicingWorkOrderDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase FindNotInvoicedWorkOrdersAction
 * Descripción: Contiene la acción de buscar las averías no facturadas para un cliente 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindNotInvoicedWorkOrdersAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String dni = Console.readString("Client dni: ");
		
		// Invoke the service
		InvoicingService cService = Factory.service.forCreateInvoiceService();
		List<InvoicingWorkOrderDto> invoicingWorkOrders = 
				cService.findWorkOrdersByClientDni(dni);
		
		// Show results
		Console.println("\nInvoice-pending work orders\n");
		if (invoicingWorkOrders.size() == 0) {
			Console.printf("There is no pending work orders\n");
		} else {
			Printer.printInvoicingWorkOrders(invoicingWorkOrders);
		}
	}

}
