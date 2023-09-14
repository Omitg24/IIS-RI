package uo.ri.cws.application.service.payroll.crud.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE NÓMINAS - Mostrar detalles de nóminas
/**
 * Título: Clase FindPayrollDetails
 * Descripción: Realiza la acción de buscar una nómina con detalles de la base 
 * de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class FindPayrollDetails implements Command<Optional<PayrollBLDto>> {
	/**
	 * Atributo id
	 */
	private String id;
	
	/**
	 * Constructor con el id de la nómina como parámetro
	 *  
	 * @param id, id de la nómina
	 */
	public FindPayrollDetails(String id) {
		ArgumentChecks.isNotBlank(id, "El id de la nómina a buscar no puede ser null ni estar vacío");
		this.id=id;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Optional<PayrollBLDto> execute() throws BusinessException {
		Optional<Payroll> op = Factory.repository.forPayroll().findById(id);
		return op.isPresent() ? Optional.ofNullable(DtoAssembler.toDto(op.get())) : Optional.empty();
	}
}
