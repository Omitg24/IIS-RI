package uo.ri.cws.application.business.payroll.crud.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

//GESTI�N DE N�MINAS - Borrar n�minas por mec�nico
/**
 * Titulo: Clase DeleteLastPayrollForMechanicTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 27 oct 2022
 */
public class DeletePayrollForMechanicTS implements Command<Void> {
	/**
	 * Atributo dni
	 */
	private String dni;
	
	/**
	 * Constructor DeleteLastPayrollForMechanicTS
	 * @param dni
	 */
	public DeletePayrollForMechanicTS(String dni) {
		checkArguments(dni);
		this.dni=dni;
	}
	
	/**
	 * M�todo checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param dni
	 */
	private void checkArguments(String dni) {
		Argument.isNotEmpty(dni, "El dni del mec�nico a borrar su n�mina no puede ser null ni estar vac�o");
	}
	
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase, DeleteLastPayrollForMechanic
	 */
	@Override
	public Void execute() throws BusinessException {
		List<String> contractIds = new ArrayList<String>();
		List<PayrollDALDto> payrolls;
		List<ContractDALDto> contracts;	
		Optional<MechanicDALDto> mechanic;
		
		mechanic = PersistenceFactory.forMechanic().findByDni(dni);
		if (mechanic.isEmpty()) {
			throw new BusinessException("El mec�nico para el que intenta borrar su n�mina mensual no existe");
		}
		contracts = PersistenceFactory.forContract().findByMechanic(mechanic.get().id);
		for (ContractDALDto contract : contracts) {
			contractIds.add(contract.id);
		}
		payrolls = PersistenceFactory.forPayroll().findByContractsIdsInDates(
				contractIds, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		if (!payrolls.isEmpty()) {
			for (PayrollDALDto payroll : payrolls) {
				PersistenceFactory.forPayroll().remove(payroll.id);
			}	
		} 
		
		return null;
	}
}
