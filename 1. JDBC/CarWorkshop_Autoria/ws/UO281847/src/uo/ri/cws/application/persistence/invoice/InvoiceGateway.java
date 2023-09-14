package uo.ri.cws.application.persistence.invoice;

import java.time.LocalDate;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;

/**
 * Titulo: Interfaz InvoiceGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public interface InvoiceGateway extends Gateway<InvoiceDALDto> {
	/**
	 * @param invoice's number
	 * @return invoice dto or null if it does not exist
	 */
	Optional<InvoiceDALDto> findByNumber(Long number);

	/**
	 * @return next invoice number to assign; that is, one greater than the greatest
	 *         number already assigned to an invoice + 1
	 * 
	 *         Notice that, in a real deployment, this way to get a new invoice
	 *         number may produce incorrect values in a concurrent environment
	 *         because two concurrent threads could get the same number. @
	 * 
	 */
	Long getNextInvoiceNumber();

	/**
	 * Titulo: Clase InvoiceDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 14 oct 2022
	 */
	public class InvoiceDALDto {
		/**
		 * Atributo id
		 */
		public String id; // the surrogate id (UUID)
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo amount
		 */
		public Double amount; // total amount (money) vat included
		/**
		 * Atributo vat
		 */
		public Double vat; // amount of vat (money)
		/**
		 * Atributo number
		 */
		public Long number; // the invoice identity, a sequential number
		/**
		 * Atributo date
		 */
		public LocalDate date; // of the invoice
		/**
		 * Atributo state
		 */
		public String state; // the state PAID, NOT_YET_PAID
	}

}