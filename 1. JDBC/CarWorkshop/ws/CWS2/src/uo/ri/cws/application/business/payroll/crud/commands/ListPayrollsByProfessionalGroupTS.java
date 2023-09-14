package uo.ri.cws.application.business.payroll.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;

//GESTI�N DE N�MINAS - Mostrar n�minas por grupo de profesionales
/**
 * Titulo: Clase ListPayrollsByProfessionalGroupTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 26 oct 2022
 */
public class ListPayrollsByProfessionalGroupTS implements Command<List<PayrollSummaryBLDto>> {
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor ListPayrollsByProfessionalGroupTS
	 * @param name
	 */
	public ListPayrollsByProfessionalGroupTS(String name) {
		checkArguments(name);
		this.name=name;
	}
	
	/**
	 * M�todo checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param name
	 */
	private void checkArguments(String name) {
		Argument.isNotEmpty(name, "El del grupo de profesionales de la n�mina a buscar no puede ser null ni estar vac�o");
	}
	
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase ListPayrollsByProfessionalGroup
	 * 
	 * @throws BusinessException
	 * @return payrolls
	 */
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {		
		List<String> contractIds = new ArrayList<String>();
		List<PayrollSummaryBLDto> payrollsSummary = new ArrayList<PayrollSummaryBLDto>();
		List<PayrollBLDto> payrolls;
		List<ContractBLDto> contracts;
		Optional<ProfessionalGroupDALDto> professionalGroup;
		
		professionalGroup = PersistenceFactory.forProfessionalGroup().findByName(name);
		if (professionalGroup.isEmpty()) {
			throw new BusinessException("El grupo de profesionales para los que intenta borrar su n�mina mensual no existe");
		}
		
		contracts = ContractAssembler.toDtoList(PersistenceFactory.forContract().findByProfessionalGroup(professionalGroup.get().id));
		for (ContractBLDto contract : contracts) {
			contractIds.add(contract.id);
		}
		
		payrolls = PayrollAssembler.toDtoList(PersistenceFactory.forPayroll().findByContractsIds(contractIds));
		for (PayrollBLDto payroll : payrolls) {
			payrollsSummary.add(PayrollAssembler.toSummaryBLDto(payroll));
		}
		return payrollsSummary;
	}
}
