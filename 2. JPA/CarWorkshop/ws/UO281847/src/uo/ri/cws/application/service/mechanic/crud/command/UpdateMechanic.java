package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase UpdateMechanic
 * Descripción: Realiza la acción de actualizar el mecánico a la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class UpdateMechanic implements Command<Void> {
	/**
	 * Atributo dto
	 */
	private MechanicDto dto;

	/**
	 * Constructor con el dto del mecánico como parámetro
	 * 
	 * @param dto, dto del mecánico
	 */
	public UpdateMechanic(MechanicDto dto) {
		ArgumentChecks.isNotNull(dto, "El dto del mecánico a actualizar no puede ser null");
		ArgumentChecks.isNotBlank(dto.dni, "El dni del mecánico a actualizar no "
				+ "puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(dto.name, "El nombre del mecánico a actualizar no "
				+ "puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(dto.surname, "El apellido del mecánico a actualizar no "
				+ "puede ser null ni estar vacío");
		this.dto = dto;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	public Void execute() throws BusinessException {
		Optional<Mechanic> om = Factory.repository.forMechanic().findById(dto.id);
		BusinessChecks.isTrue(om.isPresent(), "El mecánico a actualizar no existe");

		Mechanic m = om.get();

		m.setName(dto.name);
		m.setSurname(dto.surname);

		return null;
	}

}
