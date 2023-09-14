package uo.ri.cws.application.business.mechanic.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

/**
 * Titulo: Clase MechanicAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class MechanicAssembler {
	/**
	 * Método toMechanicDto Pasa un ResultSet a un MechanicBLDto
	 * 
	 * @param m
	 * @return dto
	 * @throws SQLException
	 */
	public static MechanicBLDto toMechanicDto(ResultSet m) throws SQLException {
		MechanicBLDto dto = new MechanicBLDto();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");

		dto.dni = m.getString("dni");
		dto.name = m.getString("name");
		dto.surname = m.getString("surname");
		return dto;
	}

	/**
	 * Método toMechanicDtoList Pasa un ResultSet a una lista de MechanicBLDto
	 * 
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	public static List<MechanicBLDto> toMechanicDtoList(ResultSet rs) throws SQLException {
		List<MechanicBLDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toMechanicDto(rs));
		}

		return res;
	}

	/**
	 * Método workOrderForInvoicingBLDto Pasa un ResultSet a un
	 * WorkOrderForInvoicingBLDto
	 * 
	 * @param rs
	 * @return dto
	 * @throws SQLException
	 */
	public static WorkOrderForInvoicingBLDto toWorkOrderForInvoicingDto(ResultSet rs) throws SQLException {
		WorkOrderForInvoicingBLDto dto = new WorkOrderForInvoicingBLDto();

		dto.id = rs.getString("id");
		dto.description = rs.getString("Description");
		Timestamp sqlDate = rs.getTimestamp("date");
		dto.date = sqlDate.toLocalDateTime();
		dto.total = rs.getDouble("amount");
		dto.state = rs.getString("status");

		return dto;
	}

	/**
	 * Método toWorkOrderForInvoicingDtoList
	 * 
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	public static List<WorkOrderForInvoicingBLDto> toWorkOrderForInvoicingDtoList(ResultSet rs) throws SQLException {
		List<WorkOrderForInvoicingBLDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toWorkOrderForInvoicingDto(rs));
		}
		return res;
	}

	/*
	 * Sesión 4
	 */
	/**
	 * Método toBLDto Pasa un MechanicDALDto a un MechanicBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<MechanicBLDto> toBLDto(Optional<MechanicDALDto> arg) {
		Optional<MechanicBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toMechanicDto(arg.get()));
		return result;
	}

	/**
	 * Método toDtoList Pasa una lista de MechanicDALDto a una lista de
	 * MechanicBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<MechanicBLDto> toDtoList(List<MechanicDALDto> arg) {
		List<MechanicBLDto> result = new ArrayList<MechanicBLDto>();
		for (MechanicDALDto mr : arg)
			result.add(toMechanicDto(mr));
		return result;
	}

	/**
	 * Método toDALDto Pasa un MechanicBLDto a un MechanicDALDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static MechanicDALDto toDALDto(MechanicBLDto arg) {
		MechanicDALDto result = new MechanicDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		return result;
	}

	/**
	 * Método toMechanicDto Pasa un MechanicDALDto a un MechanicBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	private static MechanicBLDto toMechanicDto(MechanicDALDto arg) {

		MechanicBLDto result = new MechanicBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.name = arg.name;
		result.surname = arg.surname;
		result.dni = arg.dni;
		return result;
	}
}