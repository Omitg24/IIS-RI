package uo.ri.cws.application.business.invoice.create;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.commands.CreateInvoice;
import uo.ri.cws.application.business.invoice.create.commands.FindNotInvoicedWorkOrders;

/**
 * Titulo: Clase InvoicingServiceImpl
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class InvoicingServiceImpl implements InvoicingService {
	/**
	 * M�todo createInvoiceFor
	 * @param workOrderIds
	 * @throws BusinessException
	 */
	@Override
	public InvoiceBLDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		return new CreateInvoice(workOrderIds).execute();
	}

	/**
	 * M�todo findWorkOrdersByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo findNotInvoicedWorkOrdersByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(String dni) throws BusinessException {
		return new FindNotInvoicedWorkOrders(dni).execute();
	}

	/**
	 * M�todo findWorkOrdersByPlateNumber
	 * @param plate
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo findInvoiceByNumber
	 * @param number
	 * @throws BusinessException
	 */
	@Override
	public Optional<InvoiceBLDto> findInvoiceByNumber(Long number) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * M�todo findPayMeansByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo settleInvoice
	 * @param charges
	 * @throws BusinessException
	 */
	@Override
	public void settleInvoice(String invoiceId, List<Charge_BLDto> charges) throws BusinessException {
		// TODO Auto-generated method stub

	}
}
