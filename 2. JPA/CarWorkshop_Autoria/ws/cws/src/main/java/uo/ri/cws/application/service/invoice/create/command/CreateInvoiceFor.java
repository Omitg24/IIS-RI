package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase CreateInvoiceFor
 * Descripción: Realiza la acción de crear una factura para una lista de averías
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class CreateInvoiceFor implements Command<InvoiceDto> {
	/**
	 * Atributo workOrderIds
	 */
	private List<String> workOrderIds;
	
	/**
	 * Constructor con la lista de ids de averías como parámetro
	 * 
	 * @param workOrderIds, lista de ids de averías
	 */
	public CreateInvoiceFor(List<String> workOrderIds) throws BusinessException {
		ArgumentChecks.isNotNull(workOrderIds, "La lista de ids de las averías no puede ser null");
		ArgumentChecks.isTrue(!workOrderIds.isEmpty(), "La lista de averías no existe");
		for (String workOrderId : workOrderIds) {
			ArgumentChecks.isNotBlank(workOrderId, "El id de la lista de averías no puede ser null ni estar vacío");
		}
		this.workOrderIds = workOrderIds;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public InvoiceDto execute() throws BusinessException {
		List<WorkOrder> workOrders = Factory.repository.forWorkOrder().findByIds(workOrderIds);
		BusinessChecks.isTrue(workOrderIds.size() == workOrders.size(), "Todas las averías deben de existir");
		BusinessChecks.isTrue(allFinished(workOrders), "Todas las averías deben de estar finalizadas");

		Long number = Factory.repository.forInvoice().getNextInvoiceNumber();
		number = number == null ? 0L : number; 
		
		Invoice i = new Invoice(number, workOrders);
		Factory.repository.forInvoice().add(i);

		return DtoAssembler.toDto(i);
	}
	
	/**
	 * Método allFinished
	 * Devuelve true si todas las averías están finalizadas
	 * 
	 * @param wo, averías
	 * @return true o false, si todas las averías están finalizadas
	 */
	private boolean allFinished(List<WorkOrder> wo) {
		for (WorkOrder w : wo) {
			if (!w.isFinished()) {
				return false;
			}
		}
		return true;
	}
}
