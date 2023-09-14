package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class FindMechanicsWithExpensiveRepairs implements Command<List<MechanicDto>> {

	@Override
	public List<MechanicDto> execute() throws BusinessException {
		List<Mechanic> mechanics = Factory.repository.forMechanic().findMechanicWithExpensiveRepairs();		
		return DtoAssembler.toMechanicDtoList(mechanics);
	}
}
