package uo.ri.cws.application.business.payroll.crud.commands;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTI�N DE N�MINAS - Borrar n�minas
/**
 * Titulo: Clase DeleteLastPayrollTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 27 oct 2022
 */
public class DeleteLastPayrollTS implements Command<Void> {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase, DeleteLastPayroll
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
