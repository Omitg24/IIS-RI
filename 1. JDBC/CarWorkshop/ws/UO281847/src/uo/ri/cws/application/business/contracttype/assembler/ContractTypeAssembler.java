package uo.ri.cws.application.business.contracttype.assembler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

/**
 * Titulo: Clase ContractTypeAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class ContractTypeAssembler {
	
	/**
	 * Método toBLDto 
	 * Pasa un ContractTypeDALDto a un ContractTypeBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<ContractTypeBLDto> toBLDto(Optional<ContractTypeDALDto> arg) {
		Optional<ContractTypeBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un ContractTypeDALDto a un ContractTypeBLDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ContractTypeBLDto toDto(ContractTypeDALDto contractTypeDALDto) {
		ContractTypeBLDto record = new ContractTypeBLDto();
		record.id = contractTypeDALDto.id;
		record.version = contractTypeDALDto.version;
		record.name = contractTypeDALDto.name;
		record.compensationDays = contractTypeDALDto.compensationDays;
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de ContractTypeDALDto a una lista de ContractTypeBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<ContractTypeBLDto> toDtoList(List<ContractTypeDALDto> arg) {
		List<ContractTypeBLDto> result = new ArrayList<ContractTypeBLDto>();
		for (ContractTypeDALDto mr : arg)
			result.add(toDto(mr));
		return result;
	}
}
