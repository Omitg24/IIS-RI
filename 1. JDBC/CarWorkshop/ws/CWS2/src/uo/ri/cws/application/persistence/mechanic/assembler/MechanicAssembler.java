package uo.ri.cws.application.persistence.mechanic.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

/**
 * Titulo: Clase MechanicAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class MechanicAssembler {
	/**
	 * Método toMechanicDALDto
	 * 
	 * @param m
	 * @return of
	 * @throws SQLException
	 */
	public static Optional<MechanicDALDto> toMechanicDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToMechanicDALDto(m));
		} else
			return Optional.ofNullable(null);
	}

	/**
	 * Método toMechanicDALDtoList
	 * 
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	public static List<MechanicDALDto> toMechanicDALDtoList(ResultSet rs) throws SQLException {
		List<MechanicDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToMechanicDALDto(rs));
		}

		return res;
	}

	/**
	 * Método resultSetToMechanicDALDto
	 * 
	 * @param rs
	 * @return value
	 * @throws SQLException
	 */
	private static MechanicDALDto resultSetToMechanicDALDto(ResultSet rs) throws SQLException {
		MechanicDALDto value = new MechanicDALDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");

		value.dni = rs.getString("dni");
		value.name = rs.getString("name");
		value.surname = rs.getString("surname");
		return value;
	}

}