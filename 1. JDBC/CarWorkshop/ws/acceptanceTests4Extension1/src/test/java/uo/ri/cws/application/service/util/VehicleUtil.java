package uo.ri.cws.application.service.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.service.util.sql.AddVehicleSqlUnitOfWork;

public class VehicleUtil {

	private VehicleBLDto dto = createDefaultVehicle();

	public VehicleUtil randomPlate() {
		dto.plateNumber = RandomStringUtils.randomAlphanumeric(7);
		return this;
	}

	public VehicleUtil register() {
		new AddVehicleSqlUnitOfWork(dto).execute();
		return this;
	}


	
	public VehicleBLDto get() {
		return dto;
	}

	private VehicleBLDto createDefaultVehicle() {
		VehicleBLDto res = new VehicleBLDto();
		res.id = UUID.randomUUID().toString();
		res.version = 1L;
		res.plateNumber = RandomStringUtils.randomAlphanumeric(9);
		res.make = RandomStringUtils.randomAlphabetic(4);
		res.model = RandomStringUtils.randomAlphabetic(4);
		res.clientId = RandomStringUtils.randomAlphabetic(4);

		return res;
	}

	public VehicleUtil withOwner(String id) {
		dto.clientId = id;
		return this;
	}

	public VehicleUtil withPlate(String string) {
		dto.plateNumber = string;
		return this;
	}

}
