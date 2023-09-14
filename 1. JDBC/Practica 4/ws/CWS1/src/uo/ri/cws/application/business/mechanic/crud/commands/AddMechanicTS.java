package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase AddMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class AddMechanicTS implements Command<MechanicBLDto>{

	/**
	 * Atributo mechanic
	 */
	private MechanicBLDto mechanicBLDto;

	/**
	 * Constructor Add
	 * @param mechanic
	 */
	public AddMechanicTS(MechanicBLDto mechanic) {
		this.mechanicBLDto = mechanic;
	}

	/**
	 * Método execute 
	 * @throws BusinessException
	 */
	public MechanicBLDto execute() throws BusinessException {
		this.mechanicBLDto.id = UUID.randomUUID().toString();
		this.mechanicBLDto.version = 1L;
		
		PersistenceFactory.forMechanic().add(MechanicAssembler.toDALDto(mechanicBLDto));
		
		return mechanicBLDto;
	}
}
