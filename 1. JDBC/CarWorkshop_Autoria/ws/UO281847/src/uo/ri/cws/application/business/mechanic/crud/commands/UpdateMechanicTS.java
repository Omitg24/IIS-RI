package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase UpdateMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class UpdateMechanicTS implements Command<Void> {
	/**
	 * Atributo mechanic
	 */
	private MechanicBLDto mechanicBLDto;

	/**
	 * Constructor UpdateMechanic
	 * 
	 * @param mechanic
	 */
	public UpdateMechanicTS(MechanicBLDto mechanicBLDto) {
		checkArguments(mechanicBLDto);
		this.mechanicBLDto = mechanicBLDto;
	}

	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param mechanicBLDto
	 */
	private void checkArguments(MechanicBLDto mechanicBLDto) {
		Argument.isNotNull(mechanicBLDto, "El mecánico a actualizar no puede ser null");
		Argument.isNotEmpty(mechanicBLDto.dni, "El dni del mecánico a actualizar no puede ser null ni estar vacío");
		Argument.isNotEmpty(mechanicBLDto.name, "El nombre del mecánico a actualizar no puede ser null ni estar vacío");
		Argument.isNotEmpty(mechanicBLDto.surname,
				"El apellido del mecánico a actualizar no puede ser null ni estar vacío");
	}

	/**
	 * Método execute Ejecuta la accion de la clase, update
	 * 
	 * @throws BusinessException
	 */
	public Void execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findById(mechanicBLDto.id).isEmpty()) {
			throw new BusinessException("El mecánico que intenta actualizar no existe");
		}

		PersistenceFactory.forMechanic().update(MechanicAssembler.toDALDto(mechanicBLDto));
		return null;
	}
}
