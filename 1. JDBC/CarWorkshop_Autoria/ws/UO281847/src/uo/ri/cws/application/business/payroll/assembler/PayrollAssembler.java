package uo.ri.cws.application.business.payroll.assembler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

/**
 * Titulo: Clase PayrollAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class PayrollAssembler {
	/**
	 * Método toBLDto 
	 * Pasa un PayrollDALDto a un PayrollBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<PayrollBLDto> toBLDto(Optional<PayrollDALDto> arg) {
		Optional<PayrollBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un PayrollDALDto a un PayrollBLDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static PayrollBLDto toDto(PayrollDALDto payrollDALDto) {
		PayrollBLDto record = new PayrollBLDto();
		record.id = payrollDALDto.id;
		record.version = payrollDALDto.version;
		record.contractId = payrollDALDto.contractId;
		record.date = payrollDALDto.date;
		record.monthlyWage = payrollDALDto.monthlyWage;
		record.bonus = payrollDALDto.bonus;
		record.productivityBonus = payrollDALDto.productivityBonus;
		record.trienniumPayment = payrollDALDto.trienniumPayment;
		record.incomeTax = payrollDALDto.incomeTax;
		record.nic = payrollDALDto.nic;
		record.netWage = payrollDALDto.monthlyWage + payrollDALDto.bonus + payrollDALDto.productivityBonus + payrollDALDto.trienniumPayment - 
				payrollDALDto.incomeTax - payrollDALDto.nic;
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de PayrollDALDto a una lista de PayrollBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<PayrollBLDto> toDtoList(List<PayrollDALDto> arg) {
		List<PayrollBLDto> result = new ArrayList<PayrollBLDto>();
		for (PayrollDALDto mr : arg) {
			result.add(toDto(mr));
		}
		return result;
	}

	
	/**
	 * Método toSummaryBLDto
	 * Pasa un PayrollBLDto a un PayrollSummaryBLDto 
	 * 
	 * @param payrollBLDto
	 * @return record
	 */
	public static PayrollSummaryBLDto toSummaryBLDto(PayrollBLDto payrollBLDto) {
		PayrollSummaryBLDto record = new PayrollSummaryBLDto();
		record.id = payrollBLDto.id;
		record.version = payrollBLDto.version;
		record.date = payrollBLDto.date;
		record.netWage = payrollBLDto.netWage;
		return record;
	}
}
