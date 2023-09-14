package uo.ri.cws.application.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase WorkOrdersBillingAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class WorkOrdersBillingAction implements Action {
	/**
	 * Método execute
	 */
	@Override
	public void execute() throws BusinessException {
		// Get info
		List<String> workOrderIds = new ArrayList<String>();
		do {
			String id = Console.readString("Type work order ids:  ");
			workOrderIds.add(id);
		} while (nextWorkOrder());

		// Execute logic
		InvoiceBLDto invoice = BusinessFactory.forInvoicingService().createInvoiceFor(workOrderIds);

		// Print results
		Printer.printInvoice(invoice);
	}

	/**
	 * Método nextWorkOrder read work order ids to invoice
	 * 
	 * @return boolean
	 */
	private boolean nextWorkOrder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}
}
