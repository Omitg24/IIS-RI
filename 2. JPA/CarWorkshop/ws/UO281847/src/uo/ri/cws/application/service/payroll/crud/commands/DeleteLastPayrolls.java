package uo.ri.cws.application.service.payroll.crud.commands;

import java.time.LocalDate;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Payroll;

//GESTI�N DE NÓMINAS - Borrar nóminas
/**
 * Título: Clase DeleteLastPayrolls
 * Descripción: Realiza la acción de borrar las últimas nóminas de la base de 
 * datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class DeleteLastPayrolls implements Command<Void> {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Void execute() throws BusinessException {
		List<Payroll> payrolls = Factory.repository.forPayroll().findInDates(
				LocalDate.now());
		if (!payrolls.isEmpty()) {
			for (Payroll payroll : payrolls) { 
				Factory.repository.forPayroll().remove(payroll);
			}
		} 
		return null;
	}
}
