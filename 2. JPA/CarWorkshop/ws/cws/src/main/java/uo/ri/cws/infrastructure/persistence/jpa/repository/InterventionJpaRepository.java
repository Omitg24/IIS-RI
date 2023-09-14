package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.repository.InterventionRepository;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * Título: Clase InterventionJpaRepository
 * Descripción: Contiene a la implementación de la interfaz del repositorio de 
 * intervenciones 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class InterventionJpaRepository extends BaseJpaRepository<Intervention> implements InterventionRepository {
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
	@Override
	public List<Intervention> findByMechanicIdBetweenDates(String id, LocalDateTime startDate, LocalDateTime endDate) {
		return Jpa.getManager()
				.createNamedQuery("Intervention.findByMechanicIdBetweenDates", Intervention.class)
				.setParameter(1, id)
				.setParameter(2, startDate)
				.setParameter(3, endDate)
				.getResultList();
	}

}
