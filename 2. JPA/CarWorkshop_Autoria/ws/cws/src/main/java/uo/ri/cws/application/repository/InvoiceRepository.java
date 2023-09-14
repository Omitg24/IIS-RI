package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Invoice;

/**
 * Título: Clase InvoiceRepository
 * Descripción: Contiene a la interfaz del repositorio de facturas 
 * 
 * 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface InvoiceRepository extends Repository<Invoice> {
    /**
     * Método findByNumber
     * Devuelve la factura que tiene el número pasado por parámetro
     * 
     * @param numero, numero
     * @return Invoice, factura
     */
    Optional<Invoice> findByNumber(Long numero);

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
    Long getNextInvoiceNumber();

}
