package uo.ri.cws.application.business.payroll.crud.commands;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE NÓMINAS - Borrar nóminas
/**
 * Titulo: Clase DeleteLastPayrollTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class DeleteLastPayrollTS implements Command<Void> {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, DeleteLastPayroll
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Void execute() throws BusinessException {
		List<PayrollBLDto> payrolls;
		
		payrolls = PayrollAssembler.toDtoList(PersistenceFactory.forPayroll().findInDates(
				LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		if (!payrolls.isEmpty()) {
			for (PayrollBLDto payroll : payrolls) { 
				PersistenceFactory.forPayroll().remove(payroll.id);
			}
		} 
		return null;
	}
}
