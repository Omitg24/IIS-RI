package uo.ri.cws.application.persistence.contracttype.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

/**
 * Titulo: Clase ContractTypeAssembler
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 18 oct 2022
 */
public class ContractTypeAssembler {
	/**
	 * M�todo resultSetToContractTypeDALDto 
	 * Pasa un ResultSet a un ContractTypeDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static ContractTypeDALDto resultSetToContractTypeDALDto(ResultSet rs) throws SQLException {
		ContractTypeDALDto record = new ContractTypeDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		record.name = rs.getString("name");
		record.compensationDays = rs.getDouble("compensationDays");
		return record;
	}

	/**
	 * M�todo toContractTypeList 
	 * Pasa un ResultSet a una lista de ContractTypeDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<ContractTypeDALDto> toContractTypeList(ResultSet rs) throws SQLException {
		List<ContractTypeDALDto> result = new ArrayList<ContractTypeDALDto>();
		while (rs.next()) {
			result.add(resultSetToContractTypeDALDto(rs));
		}
		return result;
	}

	/**
	 * M�todo toContractTypeDALDto 
	 * Pasa un ResultSet a un ContractTypeDALDto con posibilidad de estar vac�o
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<ContractTypeDALDto> toContractTypeDALDto(ResultSet rs) throws SQLException {
		Optional<ContractTypeDALDto> oir = Optional.empty();
		if (rs.next())
			oir = Optional.of(resultSetToContractTypeDALDto(rs));
		return oir;
	}
	
	/**
	 * M�todo toDALDto 
	 * Pasa un ContractTypeBLDto a un ContractTypeDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static ContractTypeDALDto toDALDto(ContractTypeBLDto contractTypeBLDto) {
		ContractTypeDALDto record = new ContractTypeDALDto();
		record.id = contractTypeBLDto.id;
		record.version = contractTypeBLDto.version;
		record.name = contractTypeBLDto.name;
		record.compensationDays = contractTypeBLDto.compensationDays;
		return record;
	}
}
