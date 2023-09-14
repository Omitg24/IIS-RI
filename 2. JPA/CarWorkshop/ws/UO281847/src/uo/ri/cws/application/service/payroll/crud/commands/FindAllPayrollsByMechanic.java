package uo.ri.cws.application.service.payroll.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE NÓMINAS - Mostrar nóminas por mecánico
/**
 * Título: Clase FindAllPayrollsByMechanic
 * Descripción: Realiza la acción de buscar todas las nóminas de un meánico de 
 * la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllPayrollsByMechanic implements Command<List<PayrollSummaryBLDto>> {
	/**
	 * Atributo id
	 */
	private String id;
	
	/**
	 * Constructor con el id del mecánico como parámetro 
	 * 
	 * @param id, id del mecánico
	 */
	public FindAllPayrollsByMechanic(String id) {
		ArgumentChecks.isNotBlank(id, "El id del mecánico a buscar su nómina no puede ser null ni estar vacío");
		this.id=id;
	}

	/**
	 * M�todo execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {		
		List<Payroll> payrolls =  new ArrayList<Payroll>();
		List<PayrollSummaryBLDto> payrollsSummaries = new ArrayList<PayrollSummaryBLDto>();				
		
		Optional<Mechanic> om = Factory.repository.forMechanic().findById(id);
		BusinessChecks.isTrue(om.isPresent(), "El mecánico para el que intenta buscar sus nóminas no existe");		
		
		List<Contract> contracts = Factory.repository.forContract().findByMechanicId(id);
		for (Contract contract : contracts) {
			payrolls.addAll(Factory.repository.forPayroll().findByContract(contract.getId()));
		}
		
		for (Payroll payroll : payrolls) {
			payrollsSummaries.add(DtoAssembler.toSummaryDto(payroll));
		}
		return payrollsSummaries;
	}
}
