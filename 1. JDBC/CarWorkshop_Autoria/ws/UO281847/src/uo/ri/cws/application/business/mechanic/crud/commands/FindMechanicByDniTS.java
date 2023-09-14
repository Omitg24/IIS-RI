package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase FindMechanicByIdTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class FindMechanicByDniTS implements Command<Optional<MechanicBLDto>> {
	/**
	 * Atributo dniMechanic
	 */
	private String dniMechanic;

	/**
	 * Constructor FindMechanicByIdTS
	 * 
	 * @param dniMechanic
	 */
	public FindMechanicByDniTS(String dniMechanic) {
		checkArguments(dniMechanic);
		this.dniMechanic = dniMechanic;
	}

	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param dniMechanic
	 */
	private void checkArguments(String dniMechanic) {
		Argument.isNotEmpty(dniMechanic, "El dni del mecánico a buscar no puede ser null ni estar vacío");
	}

	/**
	 * Método execute 
	 * Ejecuta la accion de la clase, findbydni
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toBLDto(PersistenceFactory.forMechanic().findByDni(dniMechanic));
	}
}
