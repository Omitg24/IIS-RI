package uo.ri.cws.application.persistence.professionalgroup.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;

/**
 * Titulo: Clase ProfessionalGroupAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class ProfessionalGroupAssembler {
	/**
	 * Método resultSetToProfessionalGroupDALDto 
	 * Pasa un ResultSet a un ProfessionalGroupDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static ProfessionalGroupDALDto resultSetToProfessionalGroupDALDto(ResultSet rs) throws SQLException {
		ProfessionalGroupDALDto record = new ProfessionalGroupDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		record.name = rs.getString("name");
		record.productivityRate = rs.getDouble("productivityBonusPercentage");
		record.trieniumSalary = rs.getDouble("trienniumPayment");
		return record;
	}

	/**
	 * Método toProfessionalGroupDALDtoList 
	 * Pasa un ResultSet a una lista de ProfessionalGroupDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<ProfessionalGroupDALDto> toProfessionalGroupDALDtoList(ResultSet rs) throws SQLException {
		List<ProfessionalGroupDALDto> result = new ArrayList<ProfessionalGroupDALDto>();
		while (rs.next()) {
			result.add(resultSetToProfessionalGroupDALDto(rs));
		}
		return result;
	}

	/**
	 * Método toProfessionalGroupDALDto 
	 * Pasa un ResultSet a un ProfessionalGroupDALDto con posibilidad de estar vacío
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<ProfessionalGroupDALDto> toProfessionalGroupDALDto(ResultSet rs) throws SQLException {
		Optional<ProfessionalGroupDALDto> oir = Optional.empty();
		if (rs.next()) {
			oir = Optional.of(resultSetToProfessionalGroupDALDto(rs));
		}
		return oir;
	}
	
	/**
	 * Método toDALDto 
	 * Pasa un ProfessionalGroupBLDto a un ProfessionalGroupDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ProfessionalGroupDALDto toDALDto(ProfessionalGroupBLDto professionalGroupBLDto) {
		ProfessionalGroupDALDto record = new ProfessionalGroupDALDto();		
		record.id = professionalGroupBLDto.id;
		record.version = professionalGroupBLDto.version;
		record.name = professionalGroupBLDto.name;
		record.productivityRate = professionalGroupBLDto.productivityRate;
		record.trieniumSalary = professionalGroupBLDto.trieniumSalary;
		return record;
	}
}
