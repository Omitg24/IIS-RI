package uo.ri.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase InvoiceWorkorderAction
 * Descripción: Contiene la acción de crear una factura para una lista de de ids
 * de averías 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class InvoiceWorkorderAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		List<String> workOrderIds = new ArrayList<>();

		// Ask the user the work order ids
		do {
			String id = Console.readString("Workorder id");
			workOrderIds.add(id);
		} while (moreWorkOrders());
		
		// Invoke service
		InvoicingService cService = Factory.service.forCreateInvoiceService();
		InvoiceDto invoice = cService.createInvoiceFor(workOrderIds);
		
		// Show result
		Console.print("\nCreated invoice\n");
		Printer.printInvoice(invoice);
	}
	
	/**
	 * Método moreWorkOrders
	 * Pregunta al usuario si quiere añadir más averías
	 * 
	 * @return true o false, respuesta del usuario
	 */
	private boolean moreWorkOrders() {
		return Console
				.readString("more work orders? (y/n) ")
				.equalsIgnoreCase("y");
	}
}
