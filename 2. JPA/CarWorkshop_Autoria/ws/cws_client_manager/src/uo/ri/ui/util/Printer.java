package uo.ri.ui.util;

import java.util.List;

import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoicingWorkOrderDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService.WorkOrderDto;
import uo.ri.util.console.Console;

/**
 * Título: Clase Printer
 * Descripción: Contiene los métodos de impresión por pantalla 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class Printer {
	/**
	 * Método printMechanic
	 * Imprime los datos de un único mecánico
	 * 
	 * @param m, mecánico
	 */
	public static void printMechanic(MechanicDto m) {
		Console.printf("\t%s %-10.10s %-15.15s %-25.25s\n"
				, m.id
				, m.dni
				, m.name
				, m.surname
		);
	}
	
	/**
	 * Método printMechanics
	 * Imprime los datos de una lista de mecánicos
	 * 
	 * @param list, lista de mecánicos
	 */
	public static void printMechanics(List<MechanicDto> list) {
		Console.printf("\t%-36s %-9s %-10s %-25s %-10s\n", 
				"Mechanic identifier", "DNI", "Name", "Surname", "Version");
		for (MechanicDto m : list) {
			printMechanic(m);
		}
	}

	/**
	 * Método printInvoice
	 * Imprime los datos de una única factura
	 * 
	 * @param invoice, factura
	 */
	public static void printInvoice(InvoiceDto invoice) {
		double importeConIVa = invoice.total;
		double iva =  invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice #: %d\n", 				invoice.number );
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", 	invoice.date);
		Console.printf("\tTotal: %.2f €\n", 			importeSinIva);
		Console.printf("\tTax: %.1f %% \n", 			invoice.vat );
		Console.printf("\tTotal, tax inc.: %.2f €\n", 	invoice.total );
		Console.printf("\tStatus: %s\n", 				invoice.state );
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
		for (InvoicingWorkOrderDto inv : list) {
			printInvoicingWorkOrder(inv);
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
	
	/**
	 * Método printContractType
	 * Imprime un único tipo de contrato
	 * 
	 * @param ct, tipo de contrato
	 */
	public static void printContractType(ContractTypeDto ct) {
		Console.printf("\t%-40.40s %-20.20s %-25f %-10s\n", 
				ct.id, ct.name, ct.compensationDays, ct.version);
	}

	/**
	 * Método printContractTypes
	 * Imprime una lista de los tipos de contrato
	 * 
	 * @param list, lista de los tripos de contrato
	 */
	public static void printContractTypes(List<ContractTypeDto> list) {
		Console.printf("\t%-40.40s %-20.20s %-25s %-10s\n", 
				"ContractType identifier", "Name", "Days of compensation", "Version");
		for (ContractTypeDto m : list) {
			printContractType(m);
		}
	}
			
	/**
	 * Método printPayroll
	 * Imprime una única nómina
	 * 
	 * @param p, nómina
	 */
	public static void printPayroll(PayrollBLDto p) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-25s %-25s %-25s %-25s %-25s %-25s %-10s\n", 
				p.id, p.date.getMonthValue(), p.date.getYear(), p.monthlyWage, 
				p.bonus, p.productivityBonus, p.trienniumPayment, p.incomeTax,
				p.nic, p.netWage, p.version);
	}
	
	/**
	 * Método printPayrolls
	 * Imprime una lista de nóminas
	 * 
	 * @param list, lista de nóminas
	 */
	public static void printPayrolls(List<PayrollBLDto> list) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-25s %-25s %-25s %-25s %-25s %-25s %-10s\n", 
				"Payroll identifier", "Month", "Year", "MonthlyWage", 
				"Bonus", "Productivity Bonus", "TrienniumPayment", "Income Tax",
				"NIC", "Net Wage", "Version");
		for (PayrollBLDto p : list) {
			printPayroll(p);
		}
	}
	
	/**
	 * Método printPayrollSummary
	 * Imprime un único resumen de una nómina
	 * 
	 * @param p, resumen de una nómina
	 */
	public static void printPayrollSummary(PayrollSummaryBLDto p) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-10s\n", 
				p.id, p.date.getMonthValue(), p.date.getYear(), p.netWage, p.version);
	}
	
	/**
	 * Método printPayrollsSummary
	 * Imprime una lista de resúmenes de nóminas
	 * 
	 * @param list, lista de resúmenes de nóminas
	 */
	public static void printPayrollsSummary(List<PayrollSummaryBLDto> list) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-10s\n", 
				"Payroll identifier", "Month", "Year", "NetWage", "Version");
		for (PayrollSummaryBLDto p : list) {
			printPayrollSummary(p);
		}
	}
	
	/**
	 * Método printPaymentMean
	 * Imprime un único método de pago
	 * 
	 * @param pm, método de pago
	 */
	private static void printPaymentMean(PaymentMeanDto pm) {
		Console.printf("\t%s \t%-8.8s \t%s \n"
				, pm.id
				, pm.getClass().getName()  // not the best...
				, pm.accumulated
		);
	}
	
	/**
	 * Método printPaymentMeans
	 * Imprime una lista de métodos de pago
	 * 
	 * @param list, lista de métodos de pago
	 */
	public static void printPaymentMeans(List<PaymentMeanDto> list) {
		Console.println();
		Console.println("Available payment means");

		Console.printf("\t%s \t%-8.8s \t%s \n", "Id", "Type", "Acummulated");
		for (PaymentMeanDto pm : list) {
			printPaymentMean( pm );
		}
	}
	
	/**
	 * Método printWorkOrder
	 * Imprime una única avería
	 * 
	 * @param wo, avería
	 */
	public static void printWorkOrder(WorkOrderDto wo) {
		Console.printf("\t%s \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n"
				, wo.id
				, wo.description
				, wo.date
				, wo.state
				, wo.total
		);
	}

	/**
	 * Método printVehicleType
	 * Imprime un único tipo de vehículo
	 * @param vt, tipo de vehículo
	 */
	public static void printVehicleType(VehicleTypeDto vt) {
		Console.printf("\t%s %-10.10s %5.2f %d\n"
				, vt.id
				, vt.name
				, vt.pricePerHour
				, vt.minTrainigHours
		);
	}
}
