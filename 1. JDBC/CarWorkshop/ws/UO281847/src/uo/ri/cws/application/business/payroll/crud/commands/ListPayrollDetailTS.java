package uo.ri.cws.application.business.payroll.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE NÓMINAS - Mostrar detalles de nóminas
/**
 * Titulo: Clase ListPayrollDetailTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class ListPayrollDetailTS implements Command<Optional<PayrollBLDto>> {
	/**
	 * Atributo id
	 */
	private String id;
	
	/**
	 * Constructor ListPayrollDetailTS 
	 * @param id
	 */
	public ListPayrollDetailTS(String id) {
		checkArguments(id);
		this.id=id;
	}
	
	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param name
	 */
	private void checkArguments(String id) {
		Argument.isNotEmpty(id, "El id de la nómina a buscar no puede ser null ni estar vacío");
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, ListPayrollDetail
	 * 
	 * @return PayrollBLDto
	 */
	@Override
	public Optional<PayrollBLDto> execute() throws BusinessException {
		return PayrollAssembler.toBLDto(PersistenceFactory.forPayroll().findById(id));
	}
}
