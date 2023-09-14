package uo.ri.ui.cashier.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase SettleInvoiceAction
 * Descripción: Contiene la acción de liquidar una factura
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class SettleInvoiceAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws Exception {
		// Ask the user for data
		Long id = Console.readLong("Invoice number ");
		
		// Invoke the service
		InvoicingService iService = Factory.service.forCreateInvoiceService();
		Optional<InvoiceDto> oi = iService.findInvoice(id);
		
		if (oi.isPresent()) {
			InvoiceDto i = oi.get();
			
			String dni = Console.readString("Client dni ");
			List<PaymentMeanDto> paymentMeans = iService.findPayMeansByClientDni(dni);
			
			if (!paymentMeans.isEmpty()) {
				Map<String, Double> charges = askForCharges(paymentMeans, i.total);
				Factory.service.forCreateInvoiceService().settleInvoice(i.id, charges);
				
				// Show result
				Printer.printInvoice(i);
				Printer.printPaymentMeans(paymentMeans);
			} else {
				Console.println("\nPaymentMeans do not exist\n");
			}			
		} else {
			Console.println("\nInvoice does not exist\n");
		}
	}
	
	/**
	 * Método askForChanges
	 * @param paymentMeans
	 * @param chargeAmount
	 * @return
	 */
	private Map<String, Double> askForCharges(List<PaymentMeanDto> paymentMeans,
			double chargeAmount) {
		Map<String, Double> charges = new HashMap<>();

		do {
			String id = Console.readString("Payment mean id ");
			Double amount = Console.readDouble("Charge amount ");
			charges.put(id, amount);
		} while (calculateAmount(charges.values()) < chargeAmount);
		
		return charges;
	}
	
	/**
	 * Método calculateAmount
	 * Calcula la cantidad de los valores
	 * 
	 * @param amountsList, lista de cantidades
	 * @return chargeAmount, cantidad
	 */
	private double calculateAmount(Collection<Double> amountsList) {
		double chargeAmount = 0;
		for (Double a : amountsList) {
			chargeAmount += a;
		}		
		return chargeAmount;
	}
}
