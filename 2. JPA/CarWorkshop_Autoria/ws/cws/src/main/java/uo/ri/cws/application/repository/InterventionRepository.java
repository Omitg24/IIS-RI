package uo.ri.cws.application.repository;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.domain.Intervention;

/**
 * Título: Clase InterventionRepository
 * Descripción: Contiene a la interfaz del repositorio de intervenciones
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public interface InterventionRepository extends Repository<Intervention> {
	/**
	 * Método findByMechanicIdBetweenDates
	 * Devuelve las intervenciones realizadas por el mecánico pasado por 
	 * parámetro y entre 2 fechas determinadas
	 * 
	 * @param id,       refers to the mechanic id
	 * @param startDate, fecha de inicio de la intervención
	 * @param endDate, fecha de finalización de la intervención
	 * @return list, intervenciones
	 */
	List<Intervention> findByMechanicIdBetweenDates(String id,
			LocalDateTime startDate, LocalDateTime endDate);	
}
