package uo.ri.cws.application.persistence.intervention.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

/**
 * Titulo: Clase InterventionAssembler
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 24 oct 2022
 */
public class InterventionAssembler {
	/**
	 * M�todo resultSetToInterventionDALDto 
	 * Pasa un ResultSet a un InterventionDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static InterventionDALDto resultSetToInterventionDALDto(ResultSet rs) throws SQLException {
		InterventionDALDto record = new InterventionDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		record.date = rs.getDate("date").toLocalDate();
		record.minutes = rs.getInt("minutes");
		record.mechanicId = rs.getString("mechanic_id");
		record.workOrderId = rs.getString("workOrder_id");		
		return record;
	}

	/**
	 * M�todo toInterventionList 
	 * Pasa un ResultSet a una lista de InterventionDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<InterventionDALDto> toInterventionList(ResultSet rs) throws SQLException {
		List<InterventionDALDto> result = new ArrayList<InterventionDALDto>();
		while (rs.next()) {
			result.add(resultSetToInterventionDALDto(rs));
		}
		return result;
	}

	/**
	 * M�todo toInterventionDALDto 
	 * Pasa un ResultSet a un InterventionDALDto con posibilidad de estar vac�o
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<InterventionDALDto> toInterventionDALDto(ResultSet rs) throws SQLException {
		Optional<InterventionDALDto> oir = Optional.empty();
		if (rs.next())
			oir = Optional.of(resultSetToInterventionDALDto(rs));
		return oir;
	}
}
