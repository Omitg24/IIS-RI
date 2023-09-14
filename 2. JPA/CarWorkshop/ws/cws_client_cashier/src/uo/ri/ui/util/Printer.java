package uo.ri.ui.util;

import java.util.List;

import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoicingWorkOrderDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.util.console.Console;

/**
 * Título: Clase Printer
 * Descripción: Contiene los métodos de impresión por pantalla 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.total;
		double iva =  invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Factura nº: %d\n", 				invoice.number );
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", 	invoice.date);
		Console.printf("\tTotal: %.2f €\n", 			importeSinIva);
		Console.printf("\tIva: %.1f %% \n", 			invoice.vat );
		Console.printf("\tTotal con IVA: %.2f €\n", 	invoice.total );
		Console.printf("\tEstado: %s\n", 				invoice.state );
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");

		Console.printf("\t%s \t%-8.8s \t%s \n", "ID", "Tipo", "Acumulado");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean( medio );
		}
	}

	private static void printPaymentMean(PaymentMeanDto medio) {
		Console.printf("\t%s \t%-8.8s \t%s \n",
				medio.id,
				medio.getClass().getName(),  // not the best...
				medio.accumulated
		);
	}

	/**
	 * Método printInvoicingWorkOrders
	 * Imprime una lista de averías facturadas
	 * 
	 * @param list, lista de averías facturadas
	 */
	public static void printInvoicingWorkOrders(List<InvoicingWorkOrderDto> list) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", 
				"Identifier", "description", "state", "total");
		for (InvoicingWorkOrderDto iwo : list) {
			printInvoicingWorkOrder(iwo);
		}
	}
	
	/**
	 * Método printInvoicingWorkOrder
	 * Imprime una única avería facturada
	 * 
	 * @param woi, avería facturada
	 */
	public static void printInvoicingWorkOrder(InvoicingWorkOrderDto iwo) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", 
				iwo.id, iwo.description, iwo.date, iwo.state, iwo.total);
	}

	//160329202
	
	public static void printMechanic(MechanicDto m) {
		Console.printf("\t%s %-10.10s %-25.25s %-25.25s\n",
				m.id
				, m.dni
				, m.name
				, m.surname
			);
	}

}
