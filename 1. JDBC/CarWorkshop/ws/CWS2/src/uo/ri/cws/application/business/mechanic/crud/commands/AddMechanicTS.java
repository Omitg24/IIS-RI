package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase AddMechanic
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class AddMechanicTS implements Command<MechanicBLDto> {
	/**
	 * Atributo mechanic
	 */
	private MechanicBLDto mechanicBLDto;

	/**
	 * Constructor Add
	 * 
	 * @param mechanicBLDto
	 */
	public AddMechanicTS(MechanicBLDto mechanicBLDto) {
		checkArguments(mechanicBLDto);
		this.mechanicBLDto = mechanicBLDto;
	}

	/**
	 * M�todo checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param mechanicBLDto
	 */
	private void checkArguments(MechanicBLDto mechanicBLDto) {
		Argument.isNotNull(mechanicBLDto, "El mec�nico a a�adir no puede ser null");
		Argument.isNotEmpty(mechanicBLDto.dni, "El dni del mec�nico a a�adir no puede ser null ni estar vac�o");
		Argument.isNotEmpty(mechanicBLDto.name, "El nombre del mec�nico a a�adir no puede ser null ni estar vac�o");
		Argument.isNotEmpty(mechanicBLDto.surname,
				"El apellido del mec�nico a a�adir no puede ser null ni estar vac�o");
	}

	/**
	 * M�todo execute Ejecuta la accion de la clase, add
	 * 
	 * @throws BusinessException
	 */
	public MechanicBLDto execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findByDni(mechanicBLDto.dni).isPresent()) {
			throw new BusinessException("El mec�nico que intenta a�adir ya existe");
		}

		this.mechanicBLDto.id = UUID.randomUUID().toString();
		this.mechanicBLDto.version = 1L;

		PersistenceFactory.forMechanic().add(MechanicAssembler.toDALDto(mechanicBLDto));

		return mechanicBLDto;
	}
}
