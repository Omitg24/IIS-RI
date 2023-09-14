package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase FindMechanicById
 * Descripción: Realiza la acción de buscar un mecánico por id de la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicById implements Command<Optional<MechanicDto>> {
	/**
	 * Atributo mechanicId
	 */
	private String mechanicId;
	
	/**
	 * Constructor con el id del mecánico a buscar como parámetro
	 * 
	 * @param mechanicId, id del mecánico
	 */
	public FindMechanicById(String mechanicId) {
		ArgumentChecks.isNotBlank(mechanicId, "El id del mecánico a buscar no puede ser null ni estar vacío");
		this.mechanicId = mechanicId;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	public Optional<MechanicDto> execute() throws BusinessException {
		Optional<Mechanic> om = Factory.repository.forMechanic().findById(mechanicId);
		return om.isPresent() ? Optional.ofNullable(DtoAssembler.toDto(om.get())) : Optional.empty();
	}
}
