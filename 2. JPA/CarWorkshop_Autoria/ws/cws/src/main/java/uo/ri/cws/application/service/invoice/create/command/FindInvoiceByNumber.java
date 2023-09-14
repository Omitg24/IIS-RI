package uo.ri.cws.application.service.invoice.create.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase FindInvoiceByNumber
 * Descripción: Realiza la acción de buscar una factura por número de la base 
 * de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindInvoiceByNumber implements Command<Optional<InvoiceDto>>{
	/**
	 * Atributo number
	 */
	private Long number;
	
	/**
	 * Constructor con el número de la factura como parámetro
	 * 
	 * @param number, número de la factura
	 */
	public FindInvoiceByNumber(Long number) {
		ArgumentChecks.isTrue(number >= 0, "El número de la factura debe ser mayor o igual que cero");
		this.number = number;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Optional<InvoiceDto> execute() throws BusinessException {
		Optional<Invoice> oi = Factory.repository.forInvoice().findByNumber(number);
		return oi.isPresent() ? Optional.ofNullable(DtoAssembler.toDto(oi.get())) : Optional.empty();
	}
}
