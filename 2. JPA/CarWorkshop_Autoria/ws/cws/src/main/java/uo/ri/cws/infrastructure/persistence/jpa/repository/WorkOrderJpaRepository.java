package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase WorkOrderJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * averías 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder> implements WorkOrderRepository {
	/**
	 * Método findByIds
	 * Devuelve una lista de las averías cuyos ids son pasados por parámetro
	 * 
	 * @param idsAveria, lista de los id de avería a recuperar
	 * @return list, lista de averías
	 */
	@Override
	public List<WorkOrder> findByIds(List<String> idsAveria) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByIds", WorkOrder.class)
				.setParameter(1, idsAveria)
				.getResultList();
	}

	/**
	 * Método findNotInvoicedWorkOrdersByClientDni
	 * Devuelve una lista de averías del dni del cliente pasado por parámetro
	 * 
	 * @param dni, dni del cliente
	 * @return list, lista de averías
	 */
	@Override
	public List<WorkOrder> findNotInvoicedWorkOrdersByClientDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findNotInvoicedWorkOrdersByClientDni", WorkOrder.class)
				.setParameter(1, dni)
				.getResultList();
	}
}
