package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase FindPayMeansByClientDni
 * Descripción: Realiza la acción de buscar todas los métodos de pago de un 
 * cliente de la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class FindPayMeansByClientDni implements Command<List<PaymentMeanDto>> {
	/**
	 * Atributo dni
	 */
	private String dni;

	/**
	 * Constructor con el dni del cliente a buscar sus métodos de pago 
	 * como parámetro
	 * 
	 * @param dni, dni del cliente
	 */
	public FindPayMeansByClientDni(String dni) {
		ArgumentChecks.isNotBlank(dni, "El dni a buscar no puede ser null ni estar vacío");
		this.dni = dni;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<PaymentMeanDto> execute() throws BusinessException {
		Optional<Client> oc = Factory.repository.forClient().findByDni(dni);
		BusinessChecks.isTrue(oc.isPresent(), "El cliente al que buscar sus métodos "
				+ "de pago no existe");
		
		Client c = oc.get();
		
		List<PaymentMean> paymentMeans = Factory.repository.forPaymentMean()
				.findByClientId(c.getId());
		return DtoAssembler.toPaymentMeanDtoList(paymentMeans);
	}
}
