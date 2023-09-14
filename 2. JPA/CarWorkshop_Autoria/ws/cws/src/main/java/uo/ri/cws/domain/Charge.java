package uo.ri.cws.domain;

import java.util.Objects;

import uo.ri.cws.domain.Invoice.InvoiceState;
import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

/**
 * Titulo: Clase Charge
 * Descripción: Contiene la entidad que corresponde a los cargos a pagar de 
 * la factura 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */	
public class Charge extends BaseEntity {
	// natural attributes
	/**
	 * Atributo amount
	 */
	private double amount = 0.0;

	// accidental attributes
	/**
	 * Atributo invoice
	 */
	private Invoice invoice;
	/**
	 * Atributo paymentMean
	 */
	private PaymentMean paymentMean;

	/**
	 * Constructor sin parámetros de la clase Charge
	 */
	Charge() {}

	/**
	 * Constructor con la factura, el método de pago y la cantidad como 
	 * parámetros de la clase Charge
	 * 
	 * @param invoice, factura
	 * @param paymentMean, método de pago
	 * @param amount, cantidad
	 */
	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {	
		// store the amount
		// increment the paymentMean accumulated -> paymentMean.pay( amount )
		// link invoice, this and paymentMean
		ArgumentChecks.isNotNull(invoice, "La factura no puede ser null");
		ArgumentChecks.isNotNull(paymentMean, "El método de pago no puede ser null");
		ArgumentChecks.isTrue(amount >= 0, "La cantidad del cargo debe ser mayor o igual que cero");
		
		this.amount = amount;
		paymentMean.pay(amount);
		Associations.ToCharge.link(paymentMean, this, invoice);
	}

	/**
	 * Método rewind
	 * Anula la relación del cargo y restablece el acumulado del método de pago
	 */
	public void rewind() {
		// asserts the invoice is not in PAID status
		// decrements the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlinks invoice, this and paymentMean
		StateChecks.isTrue(invoice.getState().equals(InvoiceState.NOT_YET_PAID));
		paymentMean.pay(-amount);
		Associations.ToCharge.unlink(this);
	}
	
	/**
	 * Método getAmount
	 * Devuelve la cantidad del pago
	 * 
	 * @return amount, cantidad
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Método setAmount
	 * Modifica la cantidad del pago
	 * 
	 * @param amount, cantidad del pago
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Método getInvoice
	 * Devuelve la factura
	 * 
	 * @return invoice, factura
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Método setInvoice
	 * Modifica la factura
	 * 
	 * @param invoice, factura
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Método getPaymentMean
	 * Devuelve el método de pago
	 * 
	 * @return paymentMean, método de pago
	 */
	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	/**
	 * Método setPaymentMean
	 * Modifica el método de pago
	 * 
	 * @param paymentMean, método de pago
	 */
	public void setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(invoice, paymentMean);
		return result;
	}

	/**
	 * Método equals 
	 * Devuelve true si dos objetos son iguales
	 * 
	 * @return true o false, en función de si los objetos son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Charge other = (Charge) obj;
		return Objects.equals(invoice, other.invoice) && Objects.equals(paymentMean, other.paymentMean);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Charge [amount=" + amount + ", invoice=" + invoice + ", paymentMean=" + paymentMean + "]";
	}	
}
