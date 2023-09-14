package uo.ri.cws.application.service.invoice.create.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Charge;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase SettleInvoice
 * Descripción: Realiza la acción de asentar la factura
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class SettleInvoice implements Command<Void> {
	/**
	 * Atributo invoiceId
	 */
	private String invoiceId;
	/**
	 * Atributo charges
	 */
	private Map<String, Double> charges;

	/**
	 * Constructor con el id de la factura y los cargos a los que asociarla 
	 * como parámetros
	 * 
	 * @param invoiceId, id de la factura
	 * @param charges, cargos
	 */
	public SettleInvoice(String invoiceId, Map<String, Double> charges) {
		ArgumentChecks.isNotBlank(invoiceId, "El id de la factura no puede ser null o estar vacío");
		ArgumentChecks.isNotNull(charges, "Los cargos de la factura no pueden ser null");
		this.invoiceId = invoiceId;
		this.charges = charges;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Void execute() throws BusinessException {
		Optional<Invoice> oi = Factory.repository.forInvoice().findById(invoiceId);
		BusinessChecks.isTrue(oi.isPresent(), "La factura a liquidar no existe");
		
		Invoice i = oi.get();
		
		List<PaymentMean> paymentMeans = new ArrayList<PaymentMean>();
		for (String paymentMeanId : charges.keySet()) {
			Optional<PaymentMean> opm = Factory.repository.forPaymentMean().findById(paymentMeanId.toString());
			BusinessChecks.isTrue(opm.isPresent(), "El método de pago no existe");
			
			PaymentMean pm = opm.get();
			BusinessChecks.isTrue(pm.isEnough(charges.get(paymentMeanId)));
			
			paymentMeans.add(pm);
		}
		
		for (PaymentMean pm : paymentMeans) {
			Factory.repository.forCharge().add(new Charge(i, pm, charges.get(pm.getId())));
		}		
		i.settle();
		return null;
	}
}
