package uo.ri.cws.application.persistence.invoice.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;

/**
 * Titulo: Clase InvoiceAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 oct 2022
 */
public class InvoiceAssembler {
	/**
	 * Método resultSetToInvoiceDALDto 
	 * Pasa un ResultSet a un InvoiceDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static InvoiceDALDto resultSetToInvoiceDALDto(ResultSet rs) throws SQLException {
		InvoiceDALDto record = new InvoiceDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.date = rs.getDate("date").toLocalDate();
		record.amount = rs.getDouble("amount");
		record.number = rs.getLong("number");
		record.vat = rs.getDouble("vat");
		record.state = rs.getString("state");
		return record;
	}

	/**
	 * Método toInvoiceList 
	 * Pasa un ResultSet a una lista de InvoiceDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<InvoiceDALDto> toInvoiceList(ResultSet rs) throws SQLException {
		List<InvoiceDALDto> result = new ArrayList<InvoiceDALDto>();
		while (rs.next()) {
			result.add(resultSetToInvoiceDALDto(rs));
		}
		return result;
	}

	/**
	 * Método toInvoiceDALDto 
	 * Pasa un ResultSet a un InvoiceDALDto con posibilidad de estar vacío
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<InvoiceDALDto> toInvoiceDALDto(ResultSet rs) throws SQLException {
		Optional<InvoiceDALDto> oir = Optional.empty();
		if (rs.next())
			oir = Optional.of(resultSetToInvoiceDALDto(rs));
		return oir;
	}
}
