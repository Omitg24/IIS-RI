package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

/**
 * Título: Clase FindAllMechanics
 * Descripción: Realiza la acción de buscar todos los mecánicos en la base de
 * datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllMechanics implements Command<List<MechanicDto>> {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */	
	public List<MechanicDto> execute() {
		return DtoAssembler.toMechanicDtoList(Factory.repository.forMechanic().findAll());
	}
}