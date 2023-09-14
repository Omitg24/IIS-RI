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
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class InvoicingServiceImpl implements InvoicingService {
	/**
	 * Método createInvoiceFor
	 * @param workOrderIds
	 * @throws BusinessException
	 */
	@Override
	public InvoiceBLDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		return new CreateInvoice(workOrderIds).execute();
	}

	/**
	 * Método findWorkOrdersByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findNotInvoicedWorkOrdersByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(String dni) throws BusinessException {
		return new FindNotInvoicedWorkOrders(dni).execute();
	}

	/**
	 * Método findWorkOrdersByPlateNumber
	 * @param plate
	 * @throws BusinessException
	 */
	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findInvoiceByNumber
	 * @param number
	 * @throws BusinessException
	 */
	@Override
	public Optional<InvoiceBLDto> findInvoiceByNumber(Long number) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findPayMeansByClientDni
	 * @param dni
	 * @throws BusinessException
	 */
	@Override
	public List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método settleInvoice
	 * @param charges
	 * @throws BusinessException
	 */
	@Override
	public void settleInvoice(String invoiceId, List<Charge_BLDto> charges) throws BusinessException {
		// TODO Auto-generated method stub

	}
}
