package uo.ri.cws.application.service.payroll.crud.commands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Payroll;

//GESTI�N DE NÓMINAS - Mostrar todas las n�minas
/**
 * Título: Clase FindAllPayrolls
 * Descripción: Realiza la acción de buscar todas las nóminas de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllPayrolls implements Command<List<PayrollSummaryBLDto>>{
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		List<PayrollSummaryBLDto> payrollsSummaries = new ArrayList<PayrollSummaryBLDto>();		
		
		List<Payroll> payrolls = Factory.repository.forPayroll().findAll();
		for (Payroll payroll : payrolls) {
			payrollsSummaries.add(DtoAssembler.toSummaryDto(payroll));
		}		
		return payrollsSummaries;
	}
}
