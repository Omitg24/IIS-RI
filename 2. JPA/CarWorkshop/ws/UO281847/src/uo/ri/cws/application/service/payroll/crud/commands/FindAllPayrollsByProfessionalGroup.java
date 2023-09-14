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
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.domain.ProfessionalGroup;
import uo.ri.util.assertion.ArgumentChecks;

//GESTIÓN DE NÓMINAS - Mostrar nóminas por grupo profesional
/**
 * Título: Clase FindAllPayrollsByProfessionalGroup
 * Descripción: Realiza la acción de buscar todas las nóminas de un grupo 
 * profesional de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllPayrollsByProfessionalGroup implements Command<List<PayrollSummaryBLDto>> {
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor con el nombre del grupo profesional como parámetro
	 * 
	 * @param name, nombre del grupo profesional
	 */
	public FindAllPayrollsByProfessionalGroup(String name) {
		ArgumentChecks.isNotBlank(name, "El del grupo de profesionales de la nómina a buscar no puede ser null ni estar vacío");
		this.name=name;
	}
	
	/**
	 * Méodo execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {		
		List<Payroll> payrolls =  new ArrayList<Payroll>();
		List<PayrollSummaryBLDto> payrollsSummaries = new ArrayList<PayrollSummaryBLDto>();				
		
		Optional<ProfessionalGroup> opg = Factory.repository.forProfessionalGroup().findByName(name);
		BusinessChecks.isTrue(opg.isPresent(), "El grupo profesional para el que intenta buscar sus nóminas no existe");		
		ProfessionalGroup pg = opg.get();
		
		List<Contract> contracts = Factory.repository.forContract().findByProfessionalGroupId(pg.getId());
		for (Contract contract : contracts) {
			payrolls.addAll(Factory.repository.forPayroll().findByContract(contract.getId()));
		}		
		
		for (Payroll payroll : payrolls) {
			payrollsSummaries.add(DtoAssembler.toSummaryDto(payroll));
		}
		return payrollsSummaries;
	}
}
