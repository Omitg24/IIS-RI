package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase InvoiceJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * facturas 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class InvoiceJpaRepository extends BaseJpaRepository<Invoice> implements InvoiceRepository {
	 /**
     * Método findByNumber
     * Devuelve la factura que tiene el número pasado por parámetro
     * 
     * @param numero, numero
     * @return Invoice, factura
     */
	@Override
	public Optional<Invoice> findByNumber(Long numero) {
		return Jpa.getManager()
				.createNamedQuery("Invoice.findByNumber", Invoice.class)
				.setParameter(1, numero)
				.getResultStream()
				.findFirst();
	}

	/**
     * Método getNextInvoiceNumber
     * Devuelve el siguiente número posible de la factura
     * 
     * @return el siguiente número de factura a usar, es decir, el mayor número
     *         existente registrado + 1.
     * 
     *         En un despliegue real esta forma de obtener el número puede dar
     *         problemas en concurrencia, ya que dos hilos simultáneos podrían
     *         llegar a obtener el mismo número. El código que use este método
     *         debería tener esto en cuenta.
     */
	@Override
	public Long getNextInvoiceNumber() {
		return Jpa.getManager()
				.createNamedQuery("Invoice.getNextInvoiceNumber", Long.class)
				.getSingleResult();
	}
}
