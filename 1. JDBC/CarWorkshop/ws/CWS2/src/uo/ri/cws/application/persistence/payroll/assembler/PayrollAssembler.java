package uo.ri.cws.application.persistence.payroll.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

/**
 * Titulo: Clase PayrollAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class PayrollAssembler {	
	/**
	 * Método resultSetToPayrollDALDto 
	 * Pasa un ResultSet a un PayrollDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	private static PayrollDALDto resultSetToPayrollDALDto(ResultSet rs) throws SQLException {
		PayrollDALDto record = new PayrollDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		record.contractId = rs.getString("contract_id");
		record.date = rs.getDate("date").toLocalDate();
		record.monthlyWage = rs.getDouble("monthlyWage");
		record.bonus = rs.getDouble("bonus");
		record.productivityBonus = rs.getDouble("productivityBonus");
		record.trienniumPayment = rs.getDouble("trienniumPayment");
		record.incomeTax = rs.getDouble("incomeTax");
		record.nic = rs.getDouble("nic");
		return record;
	}

	/**
	 * Método toPayrollDALDtoList 
	 * Pasa un ResultSet a una lista de PayrollDALDto
	 * 
	 * @param rs
	 * @return result
	 * @throws SQLException
	 */
	public static List<PayrollDALDto> toPayrollDALDtoList(ResultSet rs) throws SQLException {
		List<PayrollDALDto> result = new ArrayList<PayrollDALDto>();
		while (rs.next()) {
			result.add(resultSetToPayrollDALDto(rs));
		}
		return result;
	}

	/**
	 * Método toPayrollDALDto 
	 * Pasa un ResultSet a un PayrollDALDto con posibilidad de estar vacío
	 * 
	 * @param rs
	 * @return oir
	 * @throws SQLException
	 */
	public static Optional<PayrollDALDto> toPayrollDALDto(ResultSet rs) throws SQLException {
		Optional<PayrollDALDto> oir = Optional.empty();
		if (rs.next()) {
			oir = Optional.of(resultSetToPayrollDALDto(rs));
		}
		return oir;
	}
	
	/**
	 * Método toDALDto 
	 * Pasa un PayrollBLDto a un PayrollDALDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static PayrollDALDto toDALDto(PayrollBLDto payrollBLDto) {
		PayrollDALDto record = new PayrollDALDto();		
		record.id = payrollBLDto.id;
		record.version = payrollBLDto.version;
		record.contractId = payrollBLDto.contractId;
		record.date = payrollBLDto.date;
		record.monthlyWage = payrollBLDto.monthlyWage;
		record.bonus = payrollBLDto.bonus;
		record.productivityBonus = payrollBLDto.productivityBonus;
		record.trienniumPayment = payrollBLDto.trienniumPayment;
		record.incomeTax = payrollBLDto.incomeTax;
		record.nic = payrollBLDto.nic;
		record.netWage = payrollBLDto.netWage;
		return record;
	}
}

