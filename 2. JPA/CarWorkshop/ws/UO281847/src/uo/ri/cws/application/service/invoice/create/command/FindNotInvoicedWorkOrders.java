package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.DtoAssembler;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoicingWorkOrderDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase FindNotInvoicedWorkOrders
 * Descripción: Realiza la acción de buscar todas las averías no facturadas de 
 * un cliente de la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {
	/**
	 * Atributo dni
	 */
	private String dni;

	/**
	 * Constructor con el dni del cliente a buscar sus averías no facturadas 
	 * como parámetro
	 * 
	 * @param dni, dni del cliente
	 */
	public FindNotInvoicedWorkOrders(String dni) {
		ArgumentChecks.isNotBlank(dni, "El dni a buscar no puede ser null ni estar vacío");
		this.dni = dni;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<InvoicingWorkOrderDto> execute() throws BusinessException {
		Optional<Client> oc = Factory.repository.forClient().findByDni(dni);
		BusinessChecks.isTrue(oc.isPresent(), "El cliente al que buscar las averías "
				+ "no facturadas no existe");
		
		List<WorkOrder> invoicingWorkOrders = Factory.repository.forWorkOrder()
				.findNotInvoicedWorkOrdersByClientDni(dni);
		return DtoAssembler.toWorkOrderDtoList(invoicingWorkOrders);
	}
}
