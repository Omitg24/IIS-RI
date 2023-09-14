package uo.ri.cws.application.persistence.contract.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

/**
 * Titulo: Clase ContractTypeAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ContractAssembler {
	/**
	 * Método resultSetToContractDALDto 
	 * Pasa un ResultSet a un ContractDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static ContractDALDto resultSetToContractDALDto(ResultSet rs) throws SQLException {
		ContractDALDto record = new ContractDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		record.dni = rs.getString("mechanic_id");
		record.contractTypeName = rs.getString("contractType_id");
		record.professionalGroupName = rs.getString("professionalGroup_id");
		record.startDate = rs.getDate("startDate").toLocalDate();
		if (rs.getDate("endDate") == null) {
			record.endDate = null;
		} else {
			record.endDate = rs.getDate("endDate").toLocalDate();
		}		
		record.annualBaseWage = rs.getDouble("annualBaseWage");
		record.settlement = rs.getDouble("settlement");
		record.state = rs.getString("state");
		return record;
	}

	/**
	 * Método toContractList 
	 * Pasa un ResultSet a una lista de ContractDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<ContractDALDto> toContractList(ResultSet rs) throws SQLException {
		List<ContractDALDto> result = new ArrayList<ContractDALDto>();
		while (rs.next()) {
			result.add(resultSetToContractDALDto(rs));
		}
		return result;
	}

	/**
	 * Método toContractDALDto 
	 * Pasa un ResultSet a un ContractDALDto con posibilidad de estar vacío
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<ContractDALDto> toContractDALDto(ResultSet rs) throws SQLException {
		Optional<ContractDALDto> oir = Optional.empty();
		if (rs.next())
			oir = Optional.of(resultSetToContractDALDto(rs));
		return oir;
	}
	
	/**
	 * Método toDALDto 
	 * Pasa un ContractBLDto a un ContractDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ContractDALDto toDALDto(ContractBLDto contractTypeBLDto) {
		ContractDALDto record = new ContractDALDto();
		record.id = contractTypeBLDto.id;
		record.version = contractTypeBLDto.version;
		record.dni = contractTypeBLDto.dni;
		record.contractTypeName = contractTypeBLDto.contractTypeName;
		record.professionalGroupName = contractTypeBLDto.professionalGroupName;
		record.startDate = contractTypeBLDto.startDate;
		record.endDate = contractTypeBLDto.endDate;
		record.annualBaseWage = contractTypeBLDto.annualBaseWage;
		record.settlement = contractTypeBLDto.settlement;
		record.state = contractTypeBLDto.state.toString();
		return record;
	}
}
