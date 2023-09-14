package uo.ri.cws.application.business.contract.assembler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

/**
 * Titulo: Clase ContractAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ContractAssembler {	
	/**
	 * Método toBLDto 
	 * Pasa un ContractDALDto a un ContractBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<ContractBLDto> toBLDto(Optional<ContractDALDto> arg) {
		Optional<ContractBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un ContractBLDto a un ContractDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ContractBLDto toDto(ContractDALDto contractTypeDALDto) {
		ContractBLDto record = new ContractBLDto();
		record.id = contractTypeDALDto.id;
		record.version = contractTypeDALDto.version;
		record.dni = contractTypeDALDto.dni;
		record.contractTypeName = contractTypeDALDto.contractTypeName;
		record.professionalGroupName = contractTypeDALDto.professionalGroupName;
		record.startDate = contractTypeDALDto.startDate;
		record.endDate = contractTypeDALDto.endDate;
		record.annualBaseWage = contractTypeDALDto.annualBaseWage;
		record.settlement = contractTypeDALDto.settlement;
		record.state = ContractState.valueOf(contractTypeDALDto.state);
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de ContractDALDto a una lista de ContractBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<ContractBLDto> toDtoList(List<ContractDALDto> arg) {
		List<ContractBLDto> result = new ArrayList<ContractBLDto>();
		for (ContractDALDto mr : arg)
			result.add(toDto(mr));
		return result;
	}
}
