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
public class FindMechanicByIdTS implements Command<Optional<MechanicBLDto>> {
	/**
	 * Atributo idMechanic
	 */
	private String idMechanic;

	/**
	 * Constructor FindMechanicByIdTS
	 * 
	 * @param idMechanic
	 */
	public FindMechanicByIdTS(String idMechanic) {
		checkArguments(idMechanic);
		this.idMechanic = idMechanic;
	}

	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param idMechanic
	 */
	private void checkArguments(String idMechanic) {
		Argument.isNotEmpty(idMechanic, "El id del mecánico a buscar no puede ser null ni estar vacío");
	}

	/**
	 * Método execute Ejecuta la accion de la clase, findbyid
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toBLDto(PersistenceFactory.forMechanic().findById(idMechanic));
	}
}
