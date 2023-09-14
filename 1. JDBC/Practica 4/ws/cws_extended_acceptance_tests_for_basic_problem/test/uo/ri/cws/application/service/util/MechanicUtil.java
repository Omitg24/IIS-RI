package uo.ri.cws.application.service.util;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByIdTS;
import uo.ri.cws.application.business.util.CommandExecutor;

public class MechanicUtil {

	private MechanicBLDto dto = createDefaultDto();

	public MechanicUtil unique() {
		this.dto.dni = RandomStringUtils.randomAlphanumeric( 9 );
		this.dto.name = RandomStringUtils.randomAlphabetic(4) + "-name";
		this.dto.surname = RandomStringUtils.randomAlphabetic(4) + "-surname";
		return this;
	}

	public MechanicUtil withId(String arg) {
		this.dto.id = arg;
		return this;
	}
	
	public MechanicUtil withDni(String arg) {
		this.dto.dni = arg;
		return this;
	}

	public MechanicUtil withName(String name) {
		this.dto.name = name;
		return this;
	}

	public MechanicUtil withSurname(String arg) {
		this.dto.surname= arg;
		return this;
	}

	public MechanicUtil loadById(String id) throws BusinessException {
		this.dto = new CommandExecutor().execute( new FindMechanicByIdTS(id) )
				.orElse(null);
		return this;
	}

	
	public MechanicUtil register() throws BusinessException {
		
		dto.id = new CommandExecutor().execute( new AddMechanicTS(this.dto) ).id;
		return this;
	}

	public MechanicUtil registerIfNew() throws BusinessException {
		Optional<MechanicBLDto> op = 
				new CommandExecutor().execute( new FindMechanicByIdTS(dto.id) ); 
		if ( op.isEmpty() ) {
			register();
		}
		else {
			dto.id = op.get().id;
		}
		return this;
	}

	public MechanicBLDto get() {
		return dto;
	}

	private MechanicBLDto createDefaultDto() {
		MechanicBLDto dto = new MechanicBLDto();
		dto.name = "dummy-mechanic-name";
		dto.dni = "dummy-mechanic-dni";
		dto.surname = "dummy-mechanic-surname";
		dto.version = 1L;
		return dto;
	}

}
