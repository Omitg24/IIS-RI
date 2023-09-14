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
 * Título: Clase AddMechanic
 * Descripción: Realiza la acción de añadir el mecánico a la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class AddMechanic implements Command<MechanicDto> {
	/**
	 * Atributo dto
	 */
	private MechanicDto dto;
	
	/**
	 * Constructor que recibe el dto del mecánico de la clase AddMechanic
	 * 
	 * @param dto, dto del mecánico
	 */
	public AddMechanic(MechanicDto dto) {
		ArgumentChecks.isNotNull(dto, "El dto del mecánico a añadir no puede ser null");
		ArgumentChecks.isNotBlank(dto.dni, "El dni del mecánico a añadir no "
				+ "puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(dto.name, "El nombre del mecánico a añadir no "
				+ "puede ser null ni estar vacío");
		ArgumentChecks.isNotBlank(dto.surname, "El apellido del mecánico a añadir no "
				+ "puede ser null ni estar vacío");
		this.dto = dto;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	public MechanicDto execute() throws BusinessException {
		Optional<Mechanic> om = Factory.repository.forMechanic().findByDni(dto.dni);
		BusinessChecks.isTrue(om.isEmpty(), "El mecánico a añadir ya existe");

		Mechanic m = new Mechanic(dto.dni, dto.name, dto.surname);
		Factory.repository.forMechanic().add(m);

		dto.id = m.getId();
		return dto;
	}
}
