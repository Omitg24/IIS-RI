package uo.ri.cws.application.service.invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoicingService {
	/**
	 * Método createInvoiceFor
	 * Crea la factura
	 * 
	 * @param workOrderIds, lista de ids de averías
	 * @return Invoice, factura
	 * @throws BusinessException
	 */
    InvoiceDto createInvoiceFor(List<String> workOrderIds)
	    throws BusinessException;

    /**
     * Método findWorkOrdersByClientDni
     * Busca las averías para el dni del cliente
     * 
     * @param dni, dni del cliente
     * @return list, lista de averías
     * @throws BusinessException
     */
    List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni)
	    throws BusinessException;
    
    /**
     * Método findInvoice
     * Devuelve la factura que tiene el número introducido
     * 
     * @param number, número
     * @return Invoice, factura
     * @throws BusinessException
     */
    Optional<InvoiceDto> findInvoice(Long number) throws BusinessException;
    
    /**
     * Método findPayMeansByClientDni
     * Busca los métodos de pagos para el dni del cliente
     * 
     * @param dni, dni del cliente
     * @return list, lista de métodos de pago
     * @throws BusinessException
     */
    List<PaymentMeanDto> findPayMeansByClientDni(String dni)
	    throws BusinessException;

    /**
     * Método settleInvoice
     * Liquida la factura que tiene el id pasado por parámetro con los cargos 
     * correspondientes
     * 
     * @param invoiceId, id de la factura
     * @param charges, cargos
     * @throws BusinessException
     */
    void settleInvoice(String invoiceId, Map<String, Double> charges)
	    throws BusinessException;

    public static class InvoiceDto {

	public String id;		// the surrogate id (UUID)
	public long version;

	public double total;	// total amount (money) vat included
	public double vat;		// amount of vat (money)
	public long number;		// the invoice identity, a sequential number
	public LocalDate date;	// of the invoice
	public String state;	// the state as in InvoiceState

    }

    public static class InvoicingWorkOrderDto {
	public String id;
	public String description;
	public LocalDateTime date;
	public String state;
	public double total;
    }

    public static abstract class PaymentMeanDto {
	public String id;
	public long version;

	public String clientId;
	public Double accumulated;
    }

    public static class CashDto extends PaymentMeanDto {

    }

    public static class CardDto extends PaymentMeanDto {
	public String cardNumber;
	public LocalDate cardExpiration;
	public String cardType;

    }

    public static class VoucherDto extends PaymentMeanDto {

	public String code;
	public String description;
	public Double available;

    }
}