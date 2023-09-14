package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase FindAllMechanics
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindAllMechanicsTS implements Command<List<MechanicBLDto>> {
	/**
	 * Método execute Ejecuta la accion de la clase, findall
	 * 
	 * @return list
	 * @throws BusinessException
	 */
	public List<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toDtoList(PersistenceFactory.forMechanic().findAll());
	}
}
