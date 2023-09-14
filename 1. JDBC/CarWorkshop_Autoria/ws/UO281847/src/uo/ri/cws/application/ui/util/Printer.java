package uo.ri.cws.application.ui.util;

import java.util.List;

import console.Console;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
//import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
//import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;

/**
 * Titulo: Clase Printer
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class Printer {
	/**
	 * Método printMechanic
	 * 
	 * @param m
	 */
	public static void printMechanic(MechanicBLDto m) {
		Console.printf("\t%-36.36s %-9s %-10.10s %-25.25s %-10.2s\n", 
				m.id, m.dni, m.name, m.surname, m.version);
	}

	/**
	 * Método printMechanics
	 * 
	 * @param list
	 */
	public static void printMechanics(List<MechanicBLDto> list) {

		Console.printf("\t%-36s %-9s %-10s %-25s %-10s\n", 
				"Mechanic identifier", "DNI", "Name", "Surname", "Version");
		for (MechanicBLDto m : list)
			printMechanic(m);
	}

	/**
	 * Método printInvoice
	 * 
	 * @param i
	 */
	public static void printInvoice(InvoiceBLDto i) {
		double importeConIVa = i.total;
		double iva = i.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice number: %d%n", i.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY%n", i.date);
		Console.printf("\tAmount: %.2f %n", importeSinIva);
		Console.printf("\tVat: %.1f %% %n", i.vat);
		Console.printf("\tTotal (vat included): %.2f %n", i.total);
		Console.printf("\tState: %s%n", i.state);
	}

	/**
	 * Método printInvoicingWorkOrders
	 * 
	 * @param list
	 */
	public static void printInvoicingWorkOrders(List<WorkOrderForInvoicingBLDto> list) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", 
				"Identifier", "description", "state", "total");
		for (WorkOrderForInvoicingBLDto inv : list)
			printInvoicingWorkOrder(inv);
	}

	/**
	 * Método printInvoicingWorkOrder
	 * 
	 * @param woi
	 */
	public static void printInvoicingWorkOrder(WorkOrderForInvoicingBLDto woi) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", 
				woi.id, woi.description, woi.date, woi.state, woi.total);
	}
	
	/**
	 * Método printContractType
	 * 
	 * @param ct
	 */
	public static void printContractType(ContractTypeBLDto ct) {
		Console.printf("\t%-40.40s %-20.20s %-25f %-10s\n", 
				ct.id, ct.name, ct.compensationDays, ct.version);
	}
	
	/**
	 * Método printMechanics
	 * 
	 * @param list
	 */
	public static void printContractTypes(List<ContractTypeBLDto> list) {
		Console.printf("\t%-40.40s %-20.20s %-25s %-10s\n", 
				"ContractType identifier", "Name", "Days of compensation", "Version");
		for (ContractTypeBLDto m : list)
			printContractType(m);
	}

	/**
	 * Método printPayroll
	 * 
	 * @param p
	 */
	public static void printPayroll(PayrollBLDto p) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-25s %-25s %-25s %-25s %-25s %-25s %-10s\n", 
				p.id, p.date.getMonthValue(), p.date.getYear(), p.monthlyWage, 
				p.bonus, p.productivityBonus, p.trienniumPayment, p.incomeTax,
				p.nic, p.netWage, p.version);
	}
	
	/**
	 * Método printPayrolls
	 * 
	 * @param p
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
	 * Método printPayroll
	 * 
	 * @param p
	 */
	public static void printPayrollSummary(PayrollSummaryBLDto p) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-10s\n", 
				p.id, p.date.getMonthValue(), p.date.getYear(), p.netWage, p.version);
	}
	
	/**
	 * Método printSummaryPayrolls
	 * 
	 * @param lists
	 */
	public static void printPayrollsSummary(List<PayrollSummaryBLDto> list) {
		Console.printf("\t%-40.40s %-10.5s %-10.5s %-25s %-10s\n", 
				"Payroll identifier", "Month", "Year", "NetWage", "Version");
		for (PayrollSummaryBLDto p : list) {
			printPayrollSummary(p);
		}
	}
	
	//PRUEBA DE AUTORÍA
	/**
	 * Método printVehicles
	 * 
	 * @param v
	 */
	public static void printVehicle(VehicleBLDto v) {
		Console.printf("\n\t%-40.40s %-10.10s %-15.15s %-15.15s %-40.40s %-40.40s %-10s\n",
				v.id, v.make, v.model, v.plateNumber, v.clientId, v.vehicleTypeId, v.version);
	}
	
	/**
	 * Método printVehicles
	 * 
	 * @param list
	 */
	public static void printVehicles(List<VehicleBLDto> list) {
		Console.printf("\t%-40.40s %-10.10s %-15.15s %-15.15s %-40.40s %-40.40s %-10s",
				"Vehicle identifier", "Make", "Model", "PlateNumber", "Client identifier", "VehicleType identifier", "Version");
		for (VehicleBLDto v : list) {
			printVehicle(v);
		}
	}
}