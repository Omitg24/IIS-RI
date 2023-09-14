package uo.ri.cws.application.persistence.invoice;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import java.time.LocalDate;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface InvoiceGateway extends Gateway<InvoiceDALDto>{

	/**
	 * @param invoice's number 
	 * @return invoice dto or null if it does not exist
	 */
	Optional<InvoiceDALDto> findByNumber(Long number);
	
	/**
	 * @return next invoice number to assign; that is, one greater than the 
	 * 			greatest number already assigned to an invoice + 1 
	 * 
	 * Notice that, in a real deployment, this way to get a new invoice number 
	 * may produce incorrect values in a concurrent environment because two
	 * concurrent threads could get the same number.
	 * @ 
	 *  
	 */
	Long getNextInvoiceNumber() ;

	public class InvoiceDALDto {

		public String id;		// the surrogate id (UUID)
		public Long version;
		
		public Double amount;	// total amount (money) vat included
		public Double vat;		// amount of vat (money)
		public Long number;		// the invoice identity, a sequential number
		public LocalDate date;		// of the invoice
		public String state;	// the state PAID, NOT_YET_PAID
	}

}