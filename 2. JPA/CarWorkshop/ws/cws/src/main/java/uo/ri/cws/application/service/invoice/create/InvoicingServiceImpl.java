package uo.ri.cws.application.service.invoice.create;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.create.command.CreateInvoiceFor;
import uo.ri.cws.application.service.invoice.create.command.FindInvoiceByNumber;
import uo.ri.cws.application.service.invoice.create.command.FindNotInvoicedWorkOrders;
import uo.ri.cws.application.service.invoice.create.command.FindPayMeansByClientDni;
import uo.ri.cws.application.service.invoice.create.command.SettleInvoice;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * Título: Clase InvoicingServiceImpl
 * Descripción: Implementación de la interfaz de servicio de las facturas
 * 
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class InvoicingServiceImpl implements InvoicingService {
	/**
	 * Atributo executor
	 */
	private CommandExecutor executor = Factory.executor.forExecutor();

	/**
	 * Método createInvoiceFor
	 * Crea la factura
	 * 
	 * @param workOrderIds, lista de ids de averías
	 * @return Invoice, factura
	 * @throws BusinessException
	 */
	@Override
	public InvoiceDto createInvoiceFor(List<String> woIds) throws BusinessException {
		return executor.execute(new CreateInvoiceFor(woIds));
	}

    /**
     * Método findWorkOrdersByClientDni
     * Busca las averías para el dni del cliente
     * 
     * @param dni, dni del cliente
     * @return list, lista de averías
     * @throws BusinessException
     */
	@Override
	public List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		return executor.execute(new FindNotInvoicedWorkOrders(dni));
	}

    /**
     * Método findInvoice
     * Devuelve la factura que tiene el número introducido
     * 
     * @param number, número
     * @return Invoice, factura
     * @throws BusinessException
     */
	@Override
	public Optional<InvoiceDto> findInvoice(Long number) throws BusinessException {
		return executor.execute(new FindInvoiceByNumber(number));
	}

    /**
     * Método findPayMeansByClientDni
     * Busca los métodos de pagos para el dni del cliente
     * 
     * @param dni, dni del cliente
     * @return list, lista de métodos de pago
     * @throws BusinessException
     */
	@Override
	public List<PaymentMeanDto> findPayMeansByClientDni(String dni) throws BusinessException {
		return executor.execute(new FindPayMeansByClientDni(dni));
	}

    /**
     * Método settleInvoice
     * Liquida la factura que tiene el id pasado por parámetro con los cargos 
     * correspondientes
     * 
     * @param invoiceId, id de la factura
     * @param charges, cargos
     * @throws BusinessException
     */
	@Override
	public void settleInvoice(String invoiceId, Map<String, Double> charges) throws BusinessException {
		executor.execute(new SettleInvoice(invoiceId, charges));
	}
}
