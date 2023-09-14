package uo.ri.cws.application.service.payroll.crud.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE NÓMINAS - Borrar nóminas por mecánico
/**
 * Título: Clase DeleteLastPayrollForMechanic
 * Descripción: Realiza la acción de borrar la última nómina del mecánico de la 
 * base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class DeleteLastPayrollForMechanic implements Command<Void> {
	/**
	 * Atributo dni
	 */
	private String id;
	
	/**
	 * Constructor con el dni del mecánico como parámetro
	 * 
	 * @param dni, dni del mecánico
	 */
	public DeleteLastPayrollForMechanic(String id) {
		ArgumentChecks.isNotBlank(id, "El dni del mecánico a borrar su nómina no puede ser null ni estar vacío");
		this.id=id;
	}	
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Void execute() throws BusinessException {		
		List<String> contractIds = new ArrayList<String>();
		
		Optional<Mechanic> om = Factory.repository.forMechanic().findById(id);
		BusinessChecks.isTrue(om.isPresent(), "El mecánico para el que intenta borrar su nómina mensual no existe");
				
		List<Contract> contracts = Factory.repository.forContract().findByMechanicId(id);		
		for (Contract contract : contracts) {
			contractIds.add(contract.getId());
		}		
		
		List<Payroll> payrolls = Factory.repository.forPayroll()
				.findByContractsIdsInDates(contractIds, 
						LocalDate.now());
		if (!payrolls.isEmpty()) {
			for (Payroll payroll : payrolls) {
				Factory.repository.forPayroll().remove(payroll);
			}	
		} 		
		return null;
	}
}
