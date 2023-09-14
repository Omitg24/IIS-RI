package uo.ri.cws.application.service.payroll.crud.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Payroll;

//GESTIÓN DE NÓMINAS - Generar nóminas
/**
 * Título: Clase GeneratePayrolls
 * Descripción: Realiza la acción de generar las nóminas a los contratos 
 * actuales de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 17 nov 2022
 */
public class GeneratePayrolls implements Command<List<PayrollBLDto>> {	
	/**
	 * Atributo present
	 */
	private LocalDate present;
	
	/**
	 * Constructor con la fecha a generar la nómina como parámetro
	 * 
	 * @param present, fecha
	 */
	public GeneratePayrolls(LocalDate present) {
		if (present == null) {
			this.present = LocalDate.now();
		} else {
			this.present=present;
		}		
	}
		
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<PayrollBLDto> execute() throws BusinessException {
		List<Payroll> payrolls = new ArrayList<Payroll>();		
		
		checkIfPayrollIsAlreadyGenerated();
		
		List<Contract> contracts = Factory.repository.forContract()
				.findAllInForceThisMonth(present);
		
		for (Contract contract : contracts) {
			Payroll payroll = new Payroll(contract, present);
			Factory.repository.forPayroll().add(payroll);
			payrolls.add(payroll);
		}		
		return DtoAssembler.toPayrollDtoList(payrolls);
	}
	
	/**
	 * Método checkIfPayrollIsAlreadyGenerated
	 * Lanza una excepción si ya se han generado nóminas en este mes
	 */
	private void checkIfPayrollIsAlreadyGenerated() throws BusinessException {
		List<Payroll> payrolls = Factory.repository.forPayroll().findInDates(present);
		BusinessChecks.isTrue(payrolls.isEmpty(), "Ya existe una n�mina generada este mes");	
	}
}
