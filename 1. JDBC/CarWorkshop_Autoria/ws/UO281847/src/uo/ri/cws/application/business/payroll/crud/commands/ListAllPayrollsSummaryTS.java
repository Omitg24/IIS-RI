package uo.ri.cws.application.business.payroll.crud.commands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTI�N DE N�MINAS - Mostrar todas las n�minas
/**
 * Titulo: Clase ListAllPayrollsSummaryTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 27 oct 2022
 */
public class ListAllPayrollsSummaryTS implements Command<List<PayrollSummaryBLDto>>{
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase, ListAllPayrollsSummaries
	 * @return payrolls
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		List<PayrollSummaryBLDto> payrollsSummaries = new ArrayList<PayrollSummaryBLDto>();
		List<PayrollBLDto> payrolls;
		
		payrolls = PayrollAssembler.toDtoList(PersistenceFactory.forPayroll().findAll());
		for (PayrollBLDto payroll : payrolls) {
			payrollsSummaries.add(PayrollAssembler.toSummaryBLDto(payroll));
		}		
		return payrollsSummaries;
	}
}
