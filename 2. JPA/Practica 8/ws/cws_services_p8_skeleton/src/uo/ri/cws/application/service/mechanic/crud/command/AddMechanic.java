package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class AddMechanic implements Command<MechanicDto>{

	private MechanicDto dto;

	public AddMechanic(MechanicDto dto) {
		this.dto = dto;
	}

	public MechanicDto execute() throws BusinessException {
		Optional<Mechanic> om = Factory.repository.forMechanic().findByDni(dto.dni);
		BusinessChecks.isTrue(om.isEmpty(), "El mecánico a añadir ya existe");
		
		Mechanic m = new Mechanic(dto.dni, dto.name, dto.surname);
		Factory.repository.forMechanic().add(m);
		
		dto.id=m.getId();
		return dto;
	}

}
