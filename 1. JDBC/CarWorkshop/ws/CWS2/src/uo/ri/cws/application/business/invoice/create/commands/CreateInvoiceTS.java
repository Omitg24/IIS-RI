package uo.ri.cws.application.business.invoice.create.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import assertion.Argument;
import math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

/**
 * Titulo: Clase CreateInvoice
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class CreateInvoiceTS implements Command<InvoiceBLDto> {	
	/**
	 * Atributo workOrderIds
	 */
	private List<String> workOrderIds;

	/**
	 * Constructor CreateInvoice
	 * 
	 * @param workOrderIds
	 */
	public CreateInvoiceTS(List<String> workOrderIds) {
		checkArguments(workOrderIds);
		this.workOrderIds = workOrderIds;
	}

	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param workOrderIds
	 */
	private void checkArguments(List<String> workOrderIds) {
		Argument.isNotNull(workOrderIds, "La lista de workOrderIds no puede ser null");
		Argument.isTrue(!workOrderIds.isEmpty(), "La lista de workOrderIds no puede estar vacía");
		for (String workOrderId : workOrderIds) {
			Argument.isNotEmpty(workOrderId, "La lista de workOrderIds no puede contener elementos vacíos");
		}
	}

	/**
	 * Método execute 
	 * Ejecuta la acción de la clase, createInvoice
	 * 
	 * @return invoice
	 * @throws BusinessException
	 */
	public InvoiceBLDto execute() throws BusinessException {
		InvoiceBLDto invoice = new InvoiceBLDto();
		List<WorkOrderDALDto> workOrders = PersistenceFactory.forWorkOrder().findByIds(workOrderIds);
		
		if (!checkWorkOrdersExist(workOrders)) {
			throw new BusinessException("Workorder does not exist");
		}
		if (!checkWorkOrdersFinished(workOrders)) {
			throw new BusinessException("Workorder is not finished yet");
		}

		long numberInvoice = generateInvoiceNumber();
		LocalDate dateInvoice = LocalDate.now();
		double amount = calculateTotalInvoice(workOrderIds); // vat not included
		double vat = vatPercentage(amount, dateInvoice);
		double total = amount * (1 + vat / 100); // vat included
		total = Round.twoCents(total);

		String idInvoice = createInvoice(numberInvoice, dateInvoice, vat, total);
		updateWorkOrders(workOrders, idInvoice);

		invoice.id = idInvoice;
		invoice.total = total;
		invoice.vat = vat;
		invoice.number = numberInvoice;
		invoice.date = dateInvoice;
		invoice.state = InvoiceState.NOT_YET_PAID;

		return invoice;
	}

	/**
	 * Método createInvoice 
	 * Creates the invoice in the database; returns the id
	 * 
	 * @param numberInvoice
	 * @param dateInvoice
	 * @param vat
	 * @param total
	 * @return idInvoice
	 */
	private String createInvoice(long numberInvoice, LocalDate dateInvoice, double vat, double total) {
		InvoiceDALDto invoiceDALDto = new InvoiceDALDto();
		String idInvoice = UUID.randomUUID().toString();
		Long version = 1L;

		invoiceDALDto.id=idInvoice;
		invoiceDALDto.number=numberInvoice;
		invoiceDALDto.date=dateInvoice;
		invoiceDALDto.vat=vat;
		invoiceDALDto.amount=total;
		invoiceDALDto.state="NOT_YET_PAID";
		invoiceDALDto.version=version;
		
		PersistenceFactory.forInvoice().add(invoiceDALDto);	
		
		return idInvoice;
	}
	

	/**
	 * Método checkWorkOrderExist 
	 * checks whether every work order exist
	 * 
	 * @param workOrderIds
	 * @return boolean
	 * @throws BusinessException
	 */
	private boolean checkWorkOrdersExist(List<WorkOrderDALDto> workOrders) throws BusinessException {
		for (WorkOrderDALDto workOrder : workOrders) {
			if (workOrder.equals(null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método checkWorkOrdersFinished 
	 * checks whether every work order id is FINISHED
	 * 
	 * @param workOrderIDS
	 * @return boolean
	 * @throws BusinessException
	 */
	private boolean checkWorkOrdersFinished(List<WorkOrderDALDto> workOrders) throws BusinessException {
		for (WorkOrderDALDto workOrder : workOrders) {
			if (!workOrder.state.equalsIgnoreCase("FINISHED")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método generateInvoiceNumber 
	 * Generates next invoice number (not to be
	 * confused with the inner id)
	 * 
	 * @return long
	 */
	private Long generateInvoiceNumber() {
		return PersistenceFactory.forInvoice().getNextInvoiceNumber();
	}

	/**
	 * Método calculateTotalInvoice 
	 * Compute total amount of the invoice (as the
	 * total of individual work orders' amount
	 * 
	 * @param workOrderIDS
	 * @return totalInvoice
	 * @throws BusinessException
	 */
	private double calculateTotalInvoice(List<String> workOrderIDS) throws BusinessException {
		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/**
	 * Método getWorkOrderTotal 
	 * checks whether every work order id is FINISHED
	 * 
	 * @param workOrderID
	 * @return money
	 * @throws BusinessException
	 */
	private Double getWorkOrderTotal(String workOrderId) throws BusinessException {
		Double money = 0.0;

		Optional<WorkOrderDALDto> workOrder = PersistenceFactory.forWorkOrder().findById(workOrderId);
		if (workOrder.isEmpty()) {
			throw new BusinessException("Workorder " + workOrderId + " doesn't exist");
		}

		money = workOrder.get().amount;
		return money;
	}

	/**
	 * Método vatPercentage 
	 * returns vat percentage
	 * 
	 * @param totalInvoice
	 * @param dateInvoice
	 * @return double
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;
	}

	/**
	 * Método updateWorkOrders
	 * Actuaiza los workOrders
	 * 
	 * @param workOrders
	 * @param invoiceId
	 */
	private void updateWorkOrders(List<WorkOrderDALDto> workOrders, String invoiceId) {
		for (WorkOrderDALDto workOrder : workOrders) {
			workOrder.invoice_id=invoiceId;
			workOrder.state="INVOICED";
			PersistenceFactory.forWorkOrder().update(workOrder);
		}		
	}
}
