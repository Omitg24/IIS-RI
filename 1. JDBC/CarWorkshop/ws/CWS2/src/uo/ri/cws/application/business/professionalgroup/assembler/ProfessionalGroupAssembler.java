package uo.ri.cws.application.business.professionalgroup.assembler;

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
	 * Método toBLDto 
	 * Pasa un ProfessionalGroupDALDto a un ProfessionalGroupBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<ProfessionalGroupBLDto> toBLDto(Optional<ProfessionalGroupDALDto> arg) {
		Optional<ProfessionalGroupBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un ProfessionalGroupBLDto a un ProfessionalGroupDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ProfessionalGroupBLDto toDto(ProfessionalGroupDALDto professionalGroupDALDto) {
		ProfessionalGroupBLDto record = new ProfessionalGroupBLDto();
		record.id = professionalGroupDALDto.id;
		record.version = professionalGroupDALDto.version;
		record.name = professionalGroupDALDto.name;
		record.productivityRate = professionalGroupDALDto.productivityRate;
		record.trieniumSalary = professionalGroupDALDto.trieniumSalary;
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de ProfessionalGroupDALDto a una lista de ProfessionalGroupBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<ProfessionalGroupBLDto> toDtoList(List<ProfessionalGroupDALDto> arg) {
		List<ProfessionalGroupBLDto> result = new ArrayList<ProfessionalGroupBLDto>();
		for (ProfessionalGroupDALDto mr : arg)
			result.add(toDto(mr));
		return result;
	}
}
