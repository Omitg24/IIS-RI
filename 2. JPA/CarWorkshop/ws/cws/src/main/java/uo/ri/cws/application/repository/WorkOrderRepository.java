package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.WorkOrder;

/**
 * Título: Clase WorkOrderRepository
 * Descripción: Contiene a la interfaz del repositorio de averías
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface WorkOrderRepository extends Repository<WorkOrder>{
	/**
	 * Método findByIds
	 * Devuelve una lista de averías cuyos ids son pasados por parámetro
	 * 
	 * @param idsAveria, lista de los id de avería a recuperar
	 * @return list, lista de averías
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);
	
	/**
	 * Método findNotInvoicedWorkOrdersByClientDni
	 * Devuelve una lista de averías del dni del cliente pasado por parámetro
	 * 
	 * @param dni, dni del cliente
	 * @return list, lista de averías
	 */
	List<WorkOrder> findNotInvoicedWorkOrdersByClientDni(String dni);	
	
}