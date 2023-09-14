package uo.ri.cws.application.business.payroll.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

//GESTIÓN DE NÓMINAS - Mostrar nóminas por mecánico
/**
 * Titulo: Clase ListPayrollByMechanicTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class ListPayrollByMechanicTS implements Command<List<PayrollSummaryBLDto>> {
	/**
	 * Atributo dni
	 */
	private String dni;
	
	/**
	 * Constructor ListPayrollDetailTS 
	 * @param dni
	 */
	public ListPayrollByMechanicTS(String dni) {
		checkArguments(dni);
		this.dni=dni;
	}
	
	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param name
	 */
	private void checkArguments(String dni) {
		Argument.isNotEmpty(dni, "El dni del mecánico a buscar su nómina no puede ser null ni estar vacío");
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, ListPayrollDetail
	 * 
	 * @return PayrollBLDto
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		List<String> contractIds = new ArrayList<String>();
		List<PayrollSummaryBLDto> payrollsSummaries = new ArrayList<PayrollSummaryBLDto>();
		List<PayrollBLDto> payrolls;
		List<ContractDALDto> contracts;	
		Optional<MechanicDALDto> mechanic;
		
		mechanic = PersistenceFactory.forMechanic().findByDni(dni);
		if (mechanic.isEmpty()) {
			throw new BusinessException("El mecánico para el que intenta borrar su nómina mensual no existe");
		}
		
		contracts = PersistenceFactory.forContract().findByMechanic(mechanic.get().id);
		for (ContractDALDto contract : contracts) {
			contractIds.add(contract.id);
		}
		
		payrolls = PayrollAssembler.toDtoList(PersistenceFactory.forPayroll().findByContractsIds(contractIds));
		for (PayrollBLDto payroll : payrolls) {
			payrollsSummaries.add(PayrollAssembler.toSummaryBLDto(payroll));
		}
		return payrollsSummaries;
	}
}
