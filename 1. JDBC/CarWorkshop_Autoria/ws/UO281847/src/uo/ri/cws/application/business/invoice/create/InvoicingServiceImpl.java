package uo.ri.cws.application.business.invoice.create;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.commands.CreateInvoiceTS;
import uo.ri.cws.application.business.invoice.create.commands.FindNotInvoicedWorkOrdersTS;
import uo.ri.cws.application.business.util.CommandExecutor;

/**
 * Titulo: Clase InvoicingServiceImpl
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class InvoicingServiceImpl implements InvoicingService {
	/**
	 * M�todo createInvoiceFor Crea una factura para una lista de workOrders
	 * 
	 * @param workOrderIds
	 * @throws BusinessException
	 */
	@Override
	public InvoiceBLDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		return new CommandExecutor().execute(new CreateInvoiceTS(workOrderIds));
	}

	/**
	 * M�todo findWorkOrdersByClientDni Busca workOrders por el dni del cliente
	 * 
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo findNotInvoicedWorkOrdersByClientDni Busca workOrders sin factura por
	 * el dni del cliente
	 * 
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(String dni) throws BusinessException {
		return new CommandExecutor().execute(new FindNotInvoicedWorkOrdersTS(dni));
	}

	/**
	 * M�todo findWorkOrdersByPlateNumber Busca workOrders por el n�mero de placa
	 * 
	 * @param plate
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo findInvoiceByNumber Busca workOrders por el n�mero
	 * 
	 * @param number
	 * @throws BusinessException
	 */
	@Override
	public Optional<InvoiceBLDto> findInvoiceByNumber(Long number) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * M�todo findPayMeansByClientDni Busca payMeans por el dni del cliente
	 * 
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo settleInvoice Setea una factura dado el id y la lista de cargos
	 * 
	 * @param charges
	 * @throws BusinessException
	 */
	@Override
	public void settleInvoice(String invoiceId, List<Charge_BLDto> charges) throws BusinessException {
		// TODO Auto-generated method stub

	}
}
